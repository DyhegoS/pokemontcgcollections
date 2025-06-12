package com.reginasoft.pokemontcg.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.reginasoft.pokemontcg.entities.enums.TradeStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_trade")
public class Trade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private TradeStatus tradeStatus;
	private Instant initialDate;
	private Instant endDate;
	
	public Trade() {
	}

	public Trade(Long id, TradeStatus tradeStatus, Instant initialDate, Instant endDate) {
		super();
		this.id = id;
		this.tradeStatus = tradeStatus;
		this.initialDate = initialDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Instant getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Instant initialDate) {
		this.initialDate = initialDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, id, initialDate, tradeStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
				&& Objects.equals(initialDate, other.initialDate) && tradeStatus == other.tradeStatus;
	}
}
