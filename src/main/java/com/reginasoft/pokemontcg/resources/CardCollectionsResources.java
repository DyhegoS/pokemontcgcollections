package com.reginasoft.pokemontcg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reginasoft.pokemontcg.entities.CardCollections;
import com.reginasoft.pokemontcg.service.CardCollectionsService;

@RestController
@RequestMapping(value = "/cardcollections")
public class CardCollectionsResources {
	
	@Autowired
	private CardCollectionsService service;
	
	@GetMapping
	public ResponseEntity<List<CardCollections>> findAll(){
		List<CardCollections> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<CardCollections> findById(@PathVariable Long id){
		CardCollections obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<CardCollections> findByName(@PathVariable String name){
		CardCollections obj = service.findByName(name);
		return ResponseEntity.ok().body(obj);
	}
}
