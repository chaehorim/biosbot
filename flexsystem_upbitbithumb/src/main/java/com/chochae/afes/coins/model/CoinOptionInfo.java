package com.chochae.afes.coins.model;

public class CoinOptionInfo {
	private String fromMarket;
	private String toMarket;
	private String coinType;
	private double betAmount;
	private double betProfit;
	private boolean activeYn;
	
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
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public double getBetAmount() {
		return betAmount;
	}
	public void setBetAmount(double betAmount) {
		this.betAmount = betAmount;
	}
	public double getBetProfit() {
		return betProfit;
	}
	public void setBetProfit(double betProfit) {
		this.betProfit = betProfit;
	}
	public boolean isActiveYn() {
		return activeYn;
	}
	public void setActiveYn(boolean activeYn) {
		this.activeYn = activeYn;
	}
	@Override
	public String toString() {
		return "CoinOptionInfo [fromMarket=" + fromMarket + ", toMarket=" + toMarket + ", coinType=" + coinType
				+ ", betAmount=" + betAmount + ", betProfit=" + betProfit + ", activeYn=" + activeYn + "]";
	}
	
}
