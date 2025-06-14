package com.reginasoft.pokemontcg.entities;

import java.util.Objects;

import com.reginasoft.pokemontcg.entities.enums.TradeSide;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_trade_Item")
public class TradeItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Trade trade;
	
	@ManyToOne(optional = false)
	private Card card;
	
	@ManyToOne(optional = false)
	private CardCollections cardCollections;
	
	@Enumerated(EnumType.STRING)
	private TradeSide userSide;
	
	public TradeItem() {
	}

	public TradeItem(Long id, Trade trade, Card card, CardCollections cardCollections, TradeSide userSide) {
		super();
		this.id = id;
		this.trade = trade;
		this.card = card;
		this.cardCollections = cardCollections;
		this.userSide = userSide;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public CardCollections getCardCollections() {
		return cardCollections;
	}

	public void setCardCollections(CardCollections cardCollections) {
		this.cardCollections = cardCollections;
	}

	public TradeSide getUserSide() {
		return userSide;
	}

	public void setUserSide(TradeSide userSide) {
		this.userSide = userSide;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeItem other = (TradeItem) obj;
		return Objects.equals(id, other.id);
	}
}
