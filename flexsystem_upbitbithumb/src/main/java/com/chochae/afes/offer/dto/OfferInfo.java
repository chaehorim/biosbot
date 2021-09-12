package com.chochae.afes.offer.dto;

import java.util.ArrayList;
import java.util.List;

public class OfferInfo {

	private long	startTime;
	private String	offerId;
	private int		fromLocale;
	private int		toLocale;
	private String  fromLocaleName;
	private String  toLocaleName;
	private float	profitPercentage;
	private float 	profit;
	private float   profitEach;
	private float	exchangeRate;
	private float	buyAmout;
	private float   buyBcCnt;
	private float   sellAmount;
	private float   sellBcCnt;
	private String	userId;
	private String	statDate;
	private int		offerSerial;
	private float   buyRate;
	private float   sellRate;
	private float   outExchangeRate;
	private String 	coinType;
	
	private List<OfferDetailInfo> detailList = new ArrayList<OfferDetailInfo>();

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public int getFromLocale() {
		return fromLocale;
	}

	public void setFromLocale(int fromLocale) {
		this.fromLocale = fromLocale;
	}

	public int getToLocale() {
		return toLocale;
	}

	public void setToLocale(int toLocale) {
		this.toLocale = toLocale;
	}

	public String getFromLocaleName() {
		return fromLocaleName;
	}

	public void setFromLocaleName(String fromLocaleName) {
		this.fromLocaleName = fromLocaleName;
	}

	public String getToLocaleName() {
		return toLocaleName;
	}

	public void setToLocaleName(String toLocaleName) {
		this.toLocaleName = toLocaleName;
	}

	public float getProfitPercentage() {
		return profitPercentage;
	}

	public void setProfitPercentage(float profitPercentage) {
		this.profitPercentage = profitPercentage;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public float getBuyAmout() {
		return buyAmout;
	}

	public void setBuyAmout(float buyAmout) {
		this.buyAmout = buyAmout;
	}

	public float getBuyBcCnt() {
		return buyBcCnt;
	}

	public void setBuyBcCnt(float buyBcCnt) {
		this.buyBcCnt = buyBcCnt;
	}

	public float getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(float sellAmount) {
		this.sellAmount = sellAmount;
	}

	public float getSellBcCnt() {
		return sellBcCnt;
	}

	public void setSellBcCnt(float sellBcCnt) {
		this.sellBcCnt = sellBcCnt;
	}

	public List<OfferDetailInfo> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OfferDetailInfo> detailList) {
		this.detailList = detailList;
	}
	
	public void addDetailList(OfferDetailInfo detail) {
		this.detailList.add(detail);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public int getOfferSerial() {
		return offerSerial;
	}

	public void setOfferSerial(int offerSerial) {
		this.offerSerial = offerSerial;
	}

	public float getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(float buyRate) {
		this.buyRate = buyRate;
	}

	public float getSellRate() {
		return sellRate;
	}

	public void setSellRate(float sellRate) {
		this.sellRate = sellRate;
	}
	
	public float getOutExchangeRate() {
		return outExchangeRate;
	}

	public void setOutExchangeRate(float outExchangeRate) {
		this.outExchangeRate = outExchangeRate;
	}

	public float getProfitEach() {
		return profitEach;
	}

	public void setProfitEach(float profitEach) {
		this.profitEach = profitEach;
	}

	public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	@Override
	public String toString() {
		return "OfferInfo [startTime=" + startTime + ", offerId=" + offerId + ", fromLocale=" + fromLocale
				+ ", toLocale=" + toLocale + ", fromLocaleName=" + fromLocaleName + ", toLocaleName=" + toLocaleName
				+ ", profitPercentage=" + profitPercentage + ", profit=" + profit + ", profitEach=" + profitEach
				+ ", exchangeRate=" + exchangeRate + ", buyAmout=" + buyAmout + ", buyBcCnt=" + buyBcCnt
				+ ", sellAmount=" + sellAmount + ", sellBcCnt=" + sellBcCnt + ", userId=" + userId + ", statDate="
				+ statDate + ", offerSerial=" + offerSerial + ", buyRate=" + buyRate + ", sellRate=" + sellRate
				+ ", outExchangeRate=" + outExchangeRate + ", coinType=" + coinType + ", detailList=" + detailList
				+ "]";
	}

}
