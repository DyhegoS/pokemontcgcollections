package com.reginasoft.pokemontcg.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reginasoft.pokemontcg.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User user = new User(1L, "Fulano", "faluna@email.com", "123456");
		return ResponseEntity.ok().body(user);
	}
}
