package com.reginasoft.pokemontcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reginasoft.pokemontcg.entities.CardCollections;

public interface CardCollectionsRepository extends JpaRepository<CardCollections, Long>{

	CardCollections findByName(String name);


}
