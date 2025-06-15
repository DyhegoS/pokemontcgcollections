package com.reginasoft.pokemontcg.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.reginasoft.pokemontcg.entities.enums.TradeStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_trade")
public class Trade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TradeStatus tradeStatus;
	private Instant createdAt;
	private Instant updateAt;
	
	@ManyToOne(optional = false)
	private User userProposer;
	
	@ManyToOne(optional = false)
	private User userReceiver;
	
	@OneToMany(mappedBy = "trade", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TradeItem> userProposerItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "trade", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TradeItem> userReceiverItems = new ArrayList<>();
	
	public Trade() {
	}

	public Trade(Long id, TradeStatus tradeStatus, User userProposer, User userReceiver) {
		super();
		this.id = id;
		this.tradeStatus = tradeStatus;
		this.createdAt = Instant.now();
		this.updateAt = Instant.now();
		this.userProposer = userProposer;
		this.userReceiver = userReceiver;
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
	}

	public User getUserProposer() {
		return userProposer;
	}

	public void setUserProposer(User userProposer) {
		this.userProposer = userProposer;
	}

	public User getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(User userReceiver) {
		this.userReceiver = userReceiver;
	}

	public List<TradeItem> getUserProposerItems() {
		return userProposerItems;
	}

	public List<TradeItem> getUserReceiverItems() {
		return userReceiverItems;
	}
	
	@PrePersist
	protected void onCreate() {
		createdAt = Instant.now();
		updateAt = Instant.now();
	}
	
	protected void onUpdate() {
		updateAt = Instant.now();
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
		Trade other = (Trade) obj;
		return Objects.equals(id, other.id);
	}
}
