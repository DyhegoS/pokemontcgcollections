package com.reginasoft.pokemontcg.dto;

import java.time.Instant;
import java.util.List;

public class TradeResponseDTO {
	private Long tradeId;
	private long userProposerId;
	private long userReceiverId;
	private String tradeStatus;
	private Instant createdAt;
	private Instant updateAt;
	
	private List<TradeItemDTO> userProposerItems;
	private List<TradeItemDTO> userReceiverItems;
	
	public TradeResponseDTO() {
	}

	public TradeResponseDTO(Long tradeId, long userProposerId, long userReceiverId, String tradeStatus,
			Instant createdAt, Instant updateAt) {
		super();
		this.tradeId = tradeId;
		this.userProposerId = userProposerId;
		this.userReceiverId = userReceiverId;
		this.tradeStatus = tradeStatus;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public long getUserProposerId() {
		return userProposerId;
	}

	public void setUserProposerId(long userProposerId) {
		this.userProposerId = userProposerId;
	}

	public long getUserReceiverId() {
		return userReceiverId;
	}

	public void setUserReceiverId(long userReceiverId) {
		this.userReceiverId = userReceiverId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
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
	
	public void setUserProposerItems(List<TradeItemDTO> userProposerItems) {
		this.userProposerItems = userProposerItems;
	}
	
	public List<TradeItemDTO> getUserProposerItems() {
		return userProposerItems;
	}

	public void setUserReceiverItems(List<TradeItemDTO> userReceiverItems) {
		this.userReceiverItems = userReceiverItems;
	}

	public List<TradeItemDTO> getUserReceiverItems() {
		return userReceiverItems;
	}
}
