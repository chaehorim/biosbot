package com.chochae.afes.resource.dto;

public class AssetInfo {
	private float		amounts;
	private float		bitCnt;
	private float		totBcCnt;
	private int			locale;
	
	public AssetInfo() {
		super();
	}
	public AssetInfo(float amounts, float bitCnt, float totBcCnt, int locale) {
		super();
		this.amounts = amounts;
		this.bitCnt = bitCnt;
		this.locale = locale;
	}
	public float getAmounts() {
		return amounts;
	}
	public void setAmounts(float amounts) {
		this.amounts = amounts;
	}
	public float getBitCnt() {
		return bitCnt;
	}
	public void setBitCnt(float bitCnt) {
		this.bitCnt = bitCnt;
	}
	public int getLocale() {
		return locale;
	}
	public void setLocale(int locale) {
		this.locale = locale;
	}
	public float getTotBcCnt() {
		return totBcCnt;
	}
	public void setTotBcCnt(float totBcCnt) {
		this.totBcCnt = totBcCnt;
	}
	@Override
	public String toString() {
		return "AssetInfo [amounts=" + amounts + ", bitCnt=" + bitCnt + ", locale=" + locale + "]";
	}
	
	
}
