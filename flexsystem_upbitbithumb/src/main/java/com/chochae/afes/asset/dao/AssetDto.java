package com.chochae.afes.asset.dao;

public class AssetDto {
	private String coinType;
	private double freeAmount;
	private double totalAmount;
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public double getFreeAmount() {
		return freeAmount;
	}
	public void setFreeAmount(double freeAmount) {
		this.freeAmount = freeAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "AssetDto [coinType=" + coinType + ", freeAmount=" + freeAmount + ", totalAmount=" + totalAmount + "]";
	}
	
}
