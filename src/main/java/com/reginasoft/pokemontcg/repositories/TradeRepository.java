package com.reginasoft.pokemontcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reginasoft.pokemontcg.entities.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{

}
