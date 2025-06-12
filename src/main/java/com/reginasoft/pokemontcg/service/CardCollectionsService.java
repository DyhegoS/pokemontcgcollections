package com.reginasoft.pokemontcg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reginasoft.pokemontcg.entities.CardCollections;
import com.reginasoft.pokemontcg.repositories.CardCollectionsRepository;

@Service
public class CardCollectionsService {
	
	@Autowired
	private CardCollectionsRepository repository;
	
	public List<CardCollections> findAll(){
		return repository.findAll();
	}
	
	public CardCollections findById(Long id) {
		Optional<CardCollections> obj = repository.findById(id);
		return obj.get();
	}
	
	public CardCollections findByName(String name) {
		return repository.findByName(name);
	}
}
