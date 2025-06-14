package com.reginasoft.pokemontcg.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.reginasoft.pokemontcg.entities.Trade;
import com.reginasoft.pokemontcg.entities.enums.TradeStatus;
import com.reginasoft.pokemontcg.repositories.TradeRepository;

import jakarta.transaction.Transactional;

@Service
public class TradeService {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	public List<Trade> findAll(){
		return tradeRepository.findAll();
	}
	
	public Trade findById(Long id) {
		Optional<Trade> obj = tradeRepository.findById(id);
		return obj.get();
	}
	
	@Transactional
	public void acceptTrade(Long tradeId, Long userReceiverId) {
		Trade trade = tradeRepository.findById(tradeId)
				.orElseThrow(() -> new NotFoundException("Trade not found!"));
		
		if(!trade.getUserReceiver().getId().equals(userReceiverId)) {
			throw new UnauthorizedExpection("You are not authorized to accept this trade.");
		}
		
		if(trade.getTradeStatus() != TradeStatus.PENDING) {
			throw new InvalidOperationException("Trade os not pending.");
		}
		
		validateCardsAvailability(trade);
		
		executeTrade(trade);
		
		trade.setTradeStatus(TradeStatus.ACCEPTED);
		trade.setUpdateAt(Instant.now());
		
		tradeRepository.save(trade);
	}
}
