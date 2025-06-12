package com.reginasoft.pokemontcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reginasoft.pokemontcg.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
