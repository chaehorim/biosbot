package com.chochae.afes.user.model;

import java.util.HashMap;
import java.util.Map;

public class UserSettingInfo {
	private String userId;
	private boolean	tradeYn;
	private float minProfit;
	private int period;
	private float coinNum;
	private String coinType;
	private float exchangeRate;
	private float currency;
	private int fromCur;
	private int toCur;
	
	private Map<String, Float> outerBidMap = new HashMap<String, Float> ();
	
	public UserSettingInfo(String userId, boolean tradeYn, float minProfit, String coinType, float exchangeRate, float coinNum) {
		super();
		this.userId = userId;
		this.tradeYn = tradeYn;
		this.minProfit = minProfit;
		this.coinType = coinType;
		this.coinNum = coinNum;
		this.exchangeRate = exchangeRate;
		this.fromCur = 86;
		this.toCur = 82;
		this.currency = 1100.0f;
		initOuterBidMap();
	}
	
	public UserSettingInfo() {
		super();
	}
	
	public void initOuterBidMap() {
		outerBidMap.put("btc", 0.3f);
		outerBidMap.put("eth", 3f);
		outerBidMap.put("etc", 20f);
		outerBidMap.put("ltc", 5f);
		outerBidMap.put("bch", 1f);
	}
	
	public void resetBidMap(Map<String, Float> map) {
		outerBidMap = map;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isTradeYn() {
		return tradeYn;
	}
	public void setTradeYn(boolean tradeYn) {
		this.tradeYn = tradeYn;
	}
	public float getMinProfit() {
		return minProfit;
	}
	public void setMinProfit(float minProfit) {
		this.minProfit = minProfit;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public float getCoinNum() {
		return coinNum;
	}

	public void setCoinNum(float coinNum) {
		this.coinNum = coinNum;
	}

	public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	
	public float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Map<String, Float> getOuterBidMap() {
		return outerBidMap;
	}

	public void setOuterBidMap(Map<String, Float> outerBidMap) {
		this.outerBidMap = outerBidMap;
	}
	public int getFromCur() {
		return fromCur;
	}

	public void setFromCur(int fromCur) {
		this.fromCur = fromCur;
	}

	public int getToCur() {
		return toCur;
	}

	public void setToCur(int toCur) {
		this.toCur = toCur;
	}

	public float getCurrency() {
		return currency;
	}

	public void setCurrency(float currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "UserSettingInfo [userId=" + userId + ", tradeYn=" + tradeYn + ", minProfit=" + minProfit + ", period="
				+ period + ", coinNum=" + coinNum + ", coinType=" + coinType + ", exchangeRate=" + exchangeRate + "]";
	}

}
