package com.reginasoft.pokemontcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reginasoft.pokemontcg.entities.CardInCollection;

public interface CardInCollectionRepository extends JpaRepository<CardInCollection, Long>{
}
