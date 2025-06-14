package com.reginasoft.pokemontcg.dto;

public class TradeItemDTO {
	private Long cardId;
	private Long cardCollectionId;
	
	public TradeItemDTO() {
	}

	public TradeItemDTO(Long cardId, Long cardCollectionId) {
		super();
		this.cardId = cardId;
		this.cardCollectionId = cardCollectionId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getCardCollectionId() {
		return cardCollectionId;
	}

	public void setCardCollectionId(Long cardCollectionId) {
		this.cardCollectionId = cardCollectionId;
	}
}
