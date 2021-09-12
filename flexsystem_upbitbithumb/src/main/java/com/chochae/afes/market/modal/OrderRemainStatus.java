package com.chochae.afes.market.modal;

public class OrderRemainStatus {
	private String	marketId;
	private String	orderId;
	private float 	amount;
	private float	bitCoinCnt;
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
	public float getBitCoinCnt() {
		return bitCoinCnt;
	}
	public void setBitCoinCnt(float bitCoinCnt) {
		this.bitCoinCnt = bitCoinCnt;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "OrderRemainStatus [marketId=" + marketId + ", orderId=" + orderId + ", amount=" + amount
				+ ", bitCoinCnt=" + bitCoinCnt + "]";
	}
}
