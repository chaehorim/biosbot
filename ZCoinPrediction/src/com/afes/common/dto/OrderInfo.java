package com.afes.common.dto;

public class OrderInfo {
	private OfferInfo offer;
	private boolean buyYn;
	private boolean sellYn;
	private String buyOrderId;
	private String sellOrderId;
	private double buyPrice;
	private double sellPrice;
	private double realProfitPerc;
	public OfferInfo getOffer() {
		return offer;
	}
	public void setOffer(OfferInfo offer) {
		this.offer = offer;
	}
	public boolean isBuyYn() {
		return buyYn;
	}
	public void setBuyYn(boolean buyYn) {
		this.buyYn = buyYn;
	}
	public boolean isSellYn() {
		return sellYn;
	}
	public void setSellYn(boolean sellYn) {
		this.sellYn = sellYn;
	}
	public String getBuyOrderId() {
		return buyOrderId;
	}
	public void setBuyOrderId(String buyOrderId) {
		this.buyOrderId = buyOrderId;
	}
	public String getSellOrderId() {
		return sellOrderId;
	}
	public void setSellOrderId(String sellOrderId) {
		this.sellOrderId = sellOrderId;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getRealProfitPerc() {
		return realProfitPerc;
	}
	public void setRealProfitPerc(double realProfitPerc) {
		this.realProfitPerc = realProfitPerc;
	}
	
}
