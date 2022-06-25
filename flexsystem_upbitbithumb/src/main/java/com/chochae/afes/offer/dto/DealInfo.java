package com.chochae.afes.offer.dto;

import com.chochae.afes.common.utils.TimeDateUtil;

public class DealInfo {
	private String statDate;
	private long   time;
	private String fromMarket;
	private String toMarket;
	private String coinType;
	private double betProfit;
	private double profit;
	private double betAmount;
	private double searchBuyPrice;
	private double searchSellPrice;
	private double buyPrice;
	private double sellPrice;
	private double baseAmount;
	private double coinAmount;
	private double buyMidPrice;
	private double sellMidPrice;
	private boolean finYn;
	
	public DealInfo() {
		super();
		this.finYn = false;
	}
	
	public DealInfo(long time, String fromMarket, String toMarket, String coinType, double betProfit, double betAmount,
			double searchBuyPrice, double searchSellPrice) {
		super();
		this.statDate = TimeDateUtil.getStatDate(time);
		this.time = time;
		this.fromMarket = fromMarket;
		this.toMarket = toMarket;
		this.coinType = coinType;
		this.betProfit = betProfit;
		this.betAmount = betAmount;
		this.searchBuyPrice = searchBuyPrice;
		this.searchSellPrice = searchSellPrice;
		this.finYn = false;
	}

	public DealInfo(long time, String fromMarket, String toMarket, String coinType, double betProfit, double betAmount,
			double searchBuyPrice, double searchSellPrice, double buyMidPrice, double sellMidPrice) {
		super();
		this.statDate = TimeDateUtil.getStatDate(time);
		this.time = time;
		this.fromMarket = fromMarket;
		this.toMarket = toMarket;
		this.coinType = coinType;
		this.betProfit = betProfit;
		this.betAmount = betAmount;
		this.searchBuyPrice = searchBuyPrice;
		this.searchSellPrice = searchSellPrice;
		this.buyMidPrice = buyMidPrice;
		this.sellMidPrice = sellMidPrice;
		this.finYn = false;
	}
	
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
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
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public double getBetProfit() {
		return betProfit;
	}
	public void setBetProfit(double betProfit) {
		this.betProfit = betProfit;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getBetAmount() {
		return betAmount;
	}
	public void setBetAmount(double betAmount) {
		this.betAmount = betAmount;
	}
	public double getSearchBuyPrice() {
		return searchBuyPrice;
	}
	public void setSearchBuyPrice(double searchBuyPrice) {
		this.searchBuyPrice = searchBuyPrice;
	}
	public double getSearchSellPrice() {
		return searchSellPrice;
	}
	public void setSearchSellPrice(double searchSellPrice) {
		this.searchSellPrice = searchSellPrice;
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
	public boolean isFinYn() {
		return finYn;
	}
	public void setFinYn(boolean finYn) {
		this.finYn = finYn;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(double baseAmount) {
		this.baseAmount = baseAmount;
	}
	public double getCoinAmount() {
		return coinAmount;
	}
	public void setCoinAmount(double coinAmount) {
		this.coinAmount = coinAmount;
	}

	public double getBuyMidPrice() {
		return buyMidPrice;
	}

	public void setBuyMidPrice(double buyMidPrice) {
		this.buyMidPrice = buyMidPrice;
	}

	public double getSellMidPrice() {
		return sellMidPrice;
	}

	public void setSellMidPrice(double sellMidPrice) {
		this.sellMidPrice = sellMidPrice;
	}

	@Override
	public String toString() {
		return "DealInfo [statDate=" + statDate + ", time=" + time + ", fromMarket=" + fromMarket + ", toMarket="
				+ toMarket + ", coinType=" + coinType + ", betProfit=" + betProfit + ", profit=" + profit
				+ ", betAmount=" + betAmount + ", searchBuyPrice=" + searchBuyPrice + ", searchSellPrice="
				+ searchSellPrice + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + ", baseAmount=" + baseAmount
				+ ", coinAmount=" + coinAmount + ", buyMidPrice=" + buyMidPrice + ", sellMidPrice=" + sellMidPrice
				+ ", finYn=" + finYn + "]";
	}

	
}
