package com.reginasoft.pokemontcg.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.reginasoft.pokemontcg.entities.Card;
import com.reginasoft.pokemontcg.entities.CardCollections;
import com.reginasoft.pokemontcg.entities.CardInCollection;
import com.reginasoft.pokemontcg.entities.User;
import com.reginasoft.pokemontcg.repositories.CardCollectionsRepository;
import com.reginasoft.pokemontcg.repositories.CardInCollectionRepository;
import com.reginasoft.pokemontcg.repositories.CardRepository;
import com.reginasoft.pokemontcg.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CardCollectionsRepository cardCollectionRepository;
	
	@Autowired
	private CardInCollectionRepository cardInCollectionRepository;
	
	@Autowired
	private CardRepository cardRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Card c1 = new Card(null, "Squirtle", "Básico", 60, "https://pocket.pokemongohub.net/en/card/eieqnq8xi26zku6-squirtle", "", "Water Gun", "Water");
		Card c3 = new Card(null, "Blastoise", "Estagio 2", 150, "https://pocket.pokemongohub.net/en/card/em0jjsjn3hfsnpc-blastoise", "", "Hydro Pump", "Water");
		Card c2 = new Card(null, "Wartortle", "Estagio 1", 80, "https://pocket.pokemongohub.net/en/card/n2qq9203mln1x4p-wartortle", "", "Wave Splash", "Water");
		Card c4 = new Card(null, "Darkrai EX", "Básico", 140, "https://pocket.pokemongohub.net/en/card/a24h6da4zix1mz1-darkrai-ex", "Nightmare Aura", "Dark Prism", "Dark");
		Card c5 = new Card(null, "Wimpod", "Básico", 70, "https://pocket.pokemongohub.net/en/card/75vl23061b88y15-wimpod", "Wimp Out", "Gnaw", "Grass");
		Card c6 = new Card(null, "Golisopod", "Estagio 1", 120, "https://pocket.pokemongohub.net/en/card/qyf0u19zv1u46z4-golisopod", "", "First impression", "Grass");
		
		
		User u1 = new User(null, "Red", "red@email.com", "1234567");
		User u2 = new User(null, "Blue", "blue@email.com", "1234567");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		CardCollections cc1 = new CardCollections(null, "Genetic Apex", u1);
		CardCollections cc2 = new CardCollections(null, "SpaceTime Smackdown", u2);
		CardCollections cc3 = new CardCollections(null, "Celestial Guardians", u1);
		
		cardRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		cardCollectionRepository.saveAll(Arrays.asList(cc1, cc2, cc3));
		
		CardInCollection cic1 = new CardInCollection(null, cc1, c1, 3);
		CardInCollection cic2 = new CardInCollection(null, cc1, c2, 4);
		CardInCollection cic3 = new CardInCollection(null, cc1, c3, 2);
		CardInCollection cic4 = new CardInCollection(null, cc2, c4, 2);
		CardInCollection cic5 = new CardInCollection(null, cc3, c5, 2);
		CardInCollection cic6 = new CardInCollection(null, cc3, c6, 2);
		
		cardInCollectionRepository.saveAll(Arrays.asList(cic1, cic2, cic3, cic4, cic5, cic6));
		
		
	}
}
