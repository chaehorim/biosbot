package com.chochae.afes.resource.dto;

public class FluctuationBitCoin {
	
	private String marketId;
	private float bitcoin;
	
	public FluctuationBitCoin() {
		super();
	}
	public FluctuationBitCoin(String marketId, float bitcoin) {
		super();
		this.marketId = marketId;
		this.bitcoin = bitcoin;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public float getBitcoin() {
		return bitcoin;
	}
	public void setBitcoin(float bitcoin) {
		this.bitcoin = bitcoin;
	}
	@Override
	public String toString() {
		return "FluctuationBitCoin [marketId=" + marketId + ", bitcoin=" + bitcoin + "]";
	}
	
}
