package com.afes.common.dto;

public class OfferInfo {

	private long	time;
	private String fromMarket;
	private String toMarket;
	private String tradeCoin;
	private String baseCoin;
	private double buyVal;
	private double sellVal;
	private double amount;
	private double profitPerc;
	private double profit;
	
	
	public OfferInfo() {
		super();
	}
	public OfferInfo(long time, String fromMarket, String toMarket, String tradeCoin, String baseCoin, double buyVal, double sellVal,
			double amount, double profitPerc, double profit) {
		super();
		this.fromMarket = fromMarket;
		this.toMarket = toMarket;
		this.tradeCoin = tradeCoin;
		this.baseCoin = baseCoin;
		this.buyVal = buyVal;
		this.sellVal = sellVal;
		this.amount = amount;
		this.profitPerc = profitPerc;
		this.profit = profit;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
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
	public String getTradeCoin() {
		return tradeCoin;
	}
	public void setTradeCoin(String tradeCoin) {
		this.tradeCoin = tradeCoin;
	}
	public String getBaseCoin() {
		return baseCoin;
	}
	public void setBaseCoin(String baseCoin) {
		this.baseCoin = baseCoin;
	}
	public double getBuyVal() {
		return buyVal;
	}
	public void setBuyVal(double buyVal) {
		this.buyVal = buyVal;
	}
	public double getSellVal() {
		return sellVal;
	}
	public void setSellVal(double sellVal) {
		this.sellVal = sellVal;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getProfitPerc() {
		return profitPerc;
	}
	public void setProfitPerc(double profitPerc) {
		this.profitPerc = profitPerc;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
}
