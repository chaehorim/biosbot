package com.afes.common.dto;

import java.io.Serializable;

public class MarketCoin implements Serializable{
	private static final long serialVersionUID = -6362345780465652088L;


	private String marketid;
    
	private String coinid;

	private String baseConeId;
	
	private boolean useYn;

	public String getCoinid() {
		return coinid;
	}

	public void setCoinid(String coinid) {
		this.coinid = coinid;
	}

	public String getMarketid() {
		return marketid;
	}

	public void setMarketid(String marketid) {
		this.marketid = marketid;
	}

	public boolean isUseYn() {
		return useYn;
	}

	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
	}

	public String getBaseConeId() {
		return baseConeId;
	}

	public void setBaseConeId(String baseConeId) {
		this.baseConeId = baseConeId;
	}

}
