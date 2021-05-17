package com.chochae.afes.resource.dto;

public class TransferDTO {
	
	private String fromMarket;
	private String toMarket;
	private float  bitcoin;
	public String getFromMarket() {
		return fromMarket;
	}
	public void setFromMarket(String fromMarket) {
		this.fromMarket = fromMarket;
	}
	public String getToMarket() {
		return toMarket;
	}
	public void setToMarket(String toMarket) {
		this.toMarket = toMarket;
	}
	public float getBitcoin() {
		return bitcoin;
	}
	public void setBitcoin(float bitcoin) {
		this.bitcoin = bitcoin;
	}
	@Override
	public String toString() {
		return "TransferDTO [fromMarket=" + fromMarket + ", toMarket=" + toMarket + ", bitcoin=" + bitcoin + "]";
	}
}
