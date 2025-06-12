package com.reginasoft.pokemontcg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reginasoft.pokemontcg.entities.Card;
import com.reginasoft.pokemontcg.repositories.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	public List<Card> findAll(){
		return cardRepository.findAll();
	}
	
	public Card findById(Long id) {
		Optional<Card> obj = cardRepository.findById(id);
		return obj.get();
	}
}
