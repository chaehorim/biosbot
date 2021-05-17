package com.chochae.afes.coins.model;

public class CoinInfo {
	private String coinType;
	private String coinName;
	private int		pointLength;
	private boolean isBaseCoin;
	private boolean useYn;
	
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public int getPointLength() {
		return pointLength;
	}
	public void setPointLength(int pointLength) {
		this.pointLength = pointLength;
	}
	public boolean isBaseCoin() {
		return isBaseCoin;
	}
	public void setBaseCoin(boolean isBaseCoin) {
		this.isBaseCoin = isBaseCoin;
	}
	
	public boolean isUseYn() {
		return useYn;
	}
	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
	}
	@Override
	public String toString() {
		return "CoinInfo [coinType=" + coinType + ", coinName=" + coinName + ", pointLength=" + pointLength + "]";
	}
	
	
}
