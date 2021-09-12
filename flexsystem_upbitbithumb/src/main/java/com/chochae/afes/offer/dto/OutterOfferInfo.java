package com.chochae.afes.offer.dto;

public class OutterOfferInfo {
	private String fromMarket;
	private String toMarket;
	private int	fromCurrency;
	private int toCurrency;
	private String coinType;
	private float buyVal1;
	private float sellVal1;
	private float buyVal2;
	private float sellVal2;
	private float outterCurrency1;
	private float outterCurrency2;
	private float currency;
	private float profit1;
	private float profit2;
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
	public int getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(int fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	public int getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(int toCurrency) {
		this.toCurrency = toCurrency;
	}
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public float getBuyVal1() {
		return buyVal1;
	}
	public void setBuyVal1(float buyVal1) {
		this.buyVal1 = buyVal1;
	}
	public float getSellVal1() {
		return sellVal1;
	}
	public void setSellVal1(float sellVal1) {
		this.sellVal1 = sellVal1;
	}
	public float getBuyVal2() {
		return buyVal2;
	}
	public void setBuyVal2(float buyVal2) {
		this.buyVal2 = buyVal2;
	}
	public float getSellVal2() {
		return sellVal2;
	}
	public void setSellVal2(float sellVal2) {
		this.sellVal2 = sellVal2;
	}
	public float getOutterCurrency1() {
		return outterCurrency1;
	}
	public void setOutterCurrency1(float outterCurrency1) {
		this.outterCurrency1 = outterCurrency1;
	}
	public float getOutterCurrency2() {
		return outterCurrency2;
	}
	public void setOutterCurrency2(float outterCurrency2) {
		this.outterCurrency2 = outterCurrency2;
	}
	
	public float getCurrency() {
		return currency;
	}
	public void setCurrency(float currency) {
		this.currency = currency;
	}
	
	public float getProfit1() {
		return profit1;
	}
	public void setProfit1(float profit1) {
		this.profit1 = profit1;
	}
	public float getProfit2() {
		return profit2;
	}
	public void setProfit2(float profit2) {
		this.profit2 = profit2;
	}
	@Override
	public String toString() {
		return "OutterOfferInfo [fromMarket=" + fromMarket + ", toMarket=" + toMarket + ", fromCurrency=" + fromCurrency
				+ ", toCurrency=" + toCurrency + ", coinType=" + coinType + ", buyVal1=" + buyVal1 + ", sellVal1="
				+ sellVal1 + ", buyVal2=" + buyVal2 + ", sellVal2=" + sellVal2 + ", outterCurrency1=" + outterCurrency1
				+ ", outterCurrency2=" + outterCurrency2 + ", currency=" + currency + ", profit1=" + profit1
				+ ", profit2=" + profit2 + "]";
	}
}
