package com.reginasoft.pokemontcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reginasoft.pokemontcg.entities.TradeItem;

public interface TradeItemRepository extends JpaRepository<TradeItem, Long>{

}
