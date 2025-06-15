package com.reginasoft.pokemontcg.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.reginasoft.pokemontcg.dto.TradeItemDTO;
import com.reginasoft.pokemontcg.dto.TradeRequestDTO;
import com.reginasoft.pokemontcg.dto.TradeResponseDTO;
import com.reginasoft.pokemontcg.entities.Card;
import com.reginasoft.pokemontcg.entities.CardCollections;
import com.reginasoft.pokemontcg.entities.Trade;
import com.reginasoft.pokemontcg.entities.TradeItem;
import com.reginasoft.pokemontcg.entities.User;
import com.reginasoft.pokemontcg.entities.enums.TradeSide;
import com.reginasoft.pokemontcg.entities.enums.TradeStatus;
import com.reginasoft.pokemontcg.repositories.CardCollectionsRepository;
import com.reginasoft.pokemontcg.repositories.CardRepository;
import com.reginasoft.pokemontcg.repositories.TradeItemRepository;
import com.reginasoft.pokemontcg.repositories.TradeRepository;
import com.reginasoft.pokemontcg.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TradeService {
	
	private final TradeRepository tradeRepository;
	private final UserRepository userRepository;
	private final CardRepository cardRepository;
	private final CardCollectionsRepository cardCollectionsRepository;
	private final TradeItemRepository tradeItemRepository;
	
	@Autowired
	public TradeService(TradeRepository tradeRepository, UserRepository userRepository, CardRepository cardRepository,
			CardCollectionsRepository cardCollectionsRepository, TradeItemRepository tradeItemRepository) {
		super();
		this.tradeRepository = tradeRepository;
		this.userRepository = userRepository;
		this.cardRepository = cardRepository;
		this.cardCollectionsRepository = cardCollectionsRepository;
		this.tradeItemRepository = tradeItemRepository;
	}

	public List<Trade> findAll(){
		return tradeRepository.findAll();
	}
	
	public Trade findById(Long id) {
		Optional<Trade> obj = tradeRepository.findById(id);
		return obj.get();
	}
	
	@Transactional
	public TradeResponseDTO createTrade(Long userProposeId, TradeRequestDTO dto) {
		User userProposer = userRepository.findById(userProposeId)
				.orElseThrow(() -> new NotFoundException("Proposer not found!"));
		
		User userReceiver = userRepository.findById(dto.getUserReceiverId())
				.orElseThrow(() -> new NotFoundException("Receiver not found!"));
		
		Trade trade = new Trade(userProposeId, TradeStatus.PENDING, userProposer, userReceiver);
		trade = tradeRepository.save(trade);
		
		for(TradeItemDTO itemDTO : dto.getUserProposerItems()) {
			addTradeItem(trade, itemDTO, TradeSide.PROPOSER);
		}
		
		for(TradeItemDTO itemDTO : dto.getUserProposerItems()) {
			addTradeItem(trade, itemDTO, TradeSide.RECEIVER);
		}
		
		return toResponseDTO(trade);
	}
	
	

	private void addTradeItem(Trade trade, TradeItemDTO itemDTO, TradeSide tradeSide) {
		Card card = cardRepository.findById(itemDTO.getCardId())
				.orElseThrow(() -> new NotFoundException("Card not found!"));
		
		CardCollections cardCollections = cardCollectionsRepository.findById(itemDTO.getCardCollectionId())
				.orElseThrow(() -> new NotFoundException("Card Collection not found!"));
		
		if(!cardCollections.getCard().contains(card)) {
			throw new InvalidOperationException("Card does not belong to the collection!");
		}
		
		TradeItem item = new TradeItem(trade, card, cardCollections, tradeSide);
		tradeItemRepository.save(item);
		
		if(tradeSide == TradeSide.PROPOSER) {
			trade.getUserProposerItems().add(item);
		}
		else {
			trade.getUserReceiverItems().add(item);
		}
	}
	
	@Transactional
	public TradeResponseDTO acceptTrade(Long tradeId, Long userReceiverId) {
		Trade trade = tradeRepository.findById(tradeId)
				.orElseThrow(() -> new NotFoundException("Trade not found!"));
		
		if(!trade.getUserReceiver().getId().equals(userReceiverId)) {
			throw new UnauthorizedExpection("You are not authorized to accept this trade.");
		}
		
		if(trade.getTradeStatus() != TradeStatus.PENDING) {
			throw new InvalidOperationException("Trade os not pending.");
		}
		
		ValidateItems(trade.getUserProposerItems(), trade.getUserProposer());
		ValidateItems(trade.getUserReceiverItems(), trade.getUserReceiver());
		
		ExecuteTrade(trade);
		
		trade.setTradeStatus(TradeStatus.ACCEPTED);
		trade.setUpdateAt(Instant.now());
		
		return toResponseDTO(trade);
	}
	
	private void ValidateItems(List<TradeItem> items, User owner) {
		for(TradeItem item : items) {
			if(!item.getCardCollections().getUser().equals(owner) ||
				!item.getCardCollections().getCard().contains(item.getCard())){
					throw new InvalidOperationException("Card " + item.getCard().getId() + " is no longer available.");
				}
		}
	}
	
	private void ExecuteTrade(Trade trade) {
		for(TradeItem item : trade.getUserProposerItems()) {
			item.getCardCollections().getCard().remove(item.getCard());
			trade.getUserReceiver().getCardCollections().get(0).getCard().add(item.getCard());
		}
		
		for(TradeItem item : trade.getUserReceiverItems()) {
			item.getCardCollections().getCard().remove(item.getCard());
			trade.getUserProposer().getCardCollections().get(0).getCard().add(item.getCard());
		}
	}
	
	public TradeResponseDTO getTrade(Long tradeId) {
		Trade trade = tradeRepository.findById(tradeId)
				.orElseThrow(() -> new NotFoundException("Trade not found!"));
		return toResponseDTO(trade);
	}
	
	private TradeResponseDTO toResponseDTO(Trade trade) {
		List<TradeItemDTO> userProposerItems = trade.getUserProposerItems().stream()
				.map(item -> {
					TradeItemDTO dto = new TradeItemDTO();
					dto.setCardId(item.getCard().getId());
					dto.setCardCollectionId(item.getCardCollections().getId());
					return dto;
				}).toList();
		
		List<TradeItemDTO> userReceiverItems = trade.getUserReceiverItems().stream()
				.map(item -> {
					TradeItemDTO dto = new TradeItemDTO();
					dto.setCardId(item.getCard().getId());
					dto.setCardCollectionId(item.getCardCollections().getId());
					return dto;
				}).toList();
		
		
		TradeResponseDTO response = new TradeResponseDTO();
        response.setTradeId(trade.getId());
        response.setUserProposerId(trade.getUserProposer().getId());
        response.setUserReceiverId(trade.getUserReceiver().getId());
        response.setTradeStatus(trade.getTradeStatus().name());
        response.setCreatedAt(trade.getCreatedAt());
        response.setUpdatedAt(trade.getUpdatedAt());
        response.setUserProposerItems(userProposerItems);
        response.setUserReceiverItems(userReceiverItems);
        return response;
	}
}
