package com.reginasoft.pokemontcg.entities.enums;

public enum TradeStatus {
	PENDING(1),
	ACCEPTED(2),
	REJECTED(3);
	
	private int code;
	
	private TradeStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TradeStatus valueOf(int code) {
		for(TradeStatus value : TradeStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Valor do código de status da troca invalido!");
	}
}
