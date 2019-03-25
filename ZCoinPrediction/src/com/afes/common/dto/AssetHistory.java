package com.afes.common.dto;

public class AssetHistory {
	
	private String id;
	private String marketName;
	private String time;
	
	private double btc;
	private double eth;
	private double usdt;
	private double etc;
	private double xrp;
	private double qtum;
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getBtc() {
		return btc;
	}
	public void setBtc(double btc) {
		this.btc = btc;
	}
	public double getEth() {
		return eth;
	}
	public void setEth(double eth) {
		this.eth = eth;
	}
	public double getUsdt() {
		return usdt;
	}
	public void setUsdt(double usdt) {
		this.usdt = usdt;
	}
	public double getEtc() {
		return etc;
	}
	public void setEtc(double etc) {
		this.etc = etc;
	}
	public double getXrp() {
		return xrp;
	}
	public void setXrp(double xrp) {
		this.xrp = xrp;
	}
	public double getQtum() {
		return qtum;
	}
	public void setQtum(double qtum) {
		this.qtum = qtum;
	}
	
	
}
