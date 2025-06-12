package com.reginasoft.pokemontcg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reginasoft.pokemontcg.entities.Trade;
import com.reginasoft.pokemontcg.repositories.TradeRepository;

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
}
