package com.reginasoft.pokemontcg.dto;

import java.util.List;

public class TradeRequestDTO {
	private Long userReceiverId;
	
	private List<TradeItemDTO> userProposerItems;
	private List<TradeItemDTO> userReceiverItems;
	
	public TradeRequestDTO() {
	}

	public TradeRequestDTO(Long userReceiverId) {
		super();
		this.userReceiverId = userReceiverId;
	}

	public Long getUserReceiverId() {
		return userReceiverId;
	}

	public void setUserReceiverId(Long userReceiverId) {
		this.userReceiverId = userReceiverId;
	}

	public List<TradeItemDTO> getUserProposerItems() {
		return userProposerItems;
	}

	public List<TradeItemDTO> getUserReceiverItems() {
		return userReceiverItems;
	}
}
