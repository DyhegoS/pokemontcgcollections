package com.reginasoft.pokemontcg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reginasoft.pokemontcg.entities.Card;
import com.reginasoft.pokemontcg.service.CardService;

@RestController
@RequestMapping(value = "/cards")
public class CardResources {
	
	@Autowired
	private CardService service;
	
	@GetMapping
	public ResponseEntity<List<Card>> findAll(){
		List<Card> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Card> findById(@PathVariable Long id){
		Card obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
