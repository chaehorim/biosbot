package com.chochae.afes.resource.dto;

public class UserAssetInfo {
	
	private String userId;
	private String marketId;
	private float  amount;
	private float  bcCnt;
	private float  remainAmount;
	private float  remainBcCnt;
	
	public UserAssetInfo(String userId, String marketId, float amount, float bcCnt) {
		super();
		this.userId = userId;
		this.marketId = marketId;
		this.amount = amount;
		this.bcCnt = bcCnt;
		this.remainAmount = amount;
		this.remainBcCnt = bcCnt;
	}
	public UserAssetInfo() {
		super();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getBcCnt() {
		return bcCnt;
	}
	public void setBcCnt(float bcCnt) {
		this.bcCnt = bcCnt;
	}
	public float getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(float remainAmount) {
		this.remainAmount = remainAmount;
	}
	public float getRemainBcCnt() {
		return remainBcCnt;
	}
	public void setRemainBcCnt(float remainBcCnt) {
		this.remainBcCnt = remainBcCnt;
	}
	@Override
	public String toString() {
		return "UserAssetInfo [userId=" + userId + ", marketId=" + marketId + ", amount=" + amount + ", bcCnt=" + bcCnt
				+ ", remainAmount=" + remainAmount + ", remainBcCnt=" + remainBcCnt + "]";
	}
}
