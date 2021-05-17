package com.chochae.afes.properties.modal;

public class PropertyInfo {

	private int 		period;					// offer 생성 주기
	private float		bidBitcoin;				// offer당 비트코인 거래 양
	private boolean 	activeYn;				// offer 생성 YN
	private float		minMargin;				// offer minimum margin
	private float		minBuyMargin;			// order minimum Buying Margin
	private float		minSellMargin;			// order minimum selling Margin
	private int			orderConfirmPeriod;		// order Confirm period 오더 확인하는 시간 주
	private int			orderChangeCnt;			// order change Cnt 몇번 확인뒤 buy/sell 안되면 order가격 변경
	private int			orderChangeWaitCnt;		// order 변경뒤 기다리는 회수  orderChangeWaitCnt * orderConfirmPeriod 시간만큼 대기뒤 가격 변경
	
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public float getBidBitcoin() {
		return bidBitcoin;
	}
	public void setBidBitcoin(float bidBitcoin) {
		this.bidBitcoin = bidBitcoin;
	}
	public boolean isActiveYn() {
		return activeYn;
	}
	public void setActiveYn(boolean activeYn) {
		this.activeYn = activeYn;
	}
	public float getMinMargin() {
		return minMargin;
	}
	public void setMinMargin(float minMargin) {
		this.minMargin = minMargin;
	}
	public float getMinBuyMargin() {
		return minBuyMargin;
	}
	public void setMinBuyMargin(float minBuyMargin) {
		this.minBuyMargin = minBuyMargin;
	}
	public float getMinSellMargin() {
		return minSellMargin;
	}
	public void setMinSellMargin(float minSellMargin) {
		this.minSellMargin = minSellMargin;
	}
	public int getOrderConfirmPeriod() {
		return orderConfirmPeriod;
	}
	public void setOrderConfirmPeriod(int orderConfirmPeriod) {
		this.orderConfirmPeriod = orderConfirmPeriod;
	}
	public int getOrderChangeCnt() {
		return orderChangeCnt;
	}
	public void setOrderChangeCnt(int orderChangeCnt) {
		this.orderChangeCnt = orderChangeCnt;
	}
	public int getOrderChangeWaitCnt() {
		return orderChangeWaitCnt;
	}
	public void setOrderChangeWaitCnt(int orderChangeWaitCnt) {
		this.orderChangeWaitCnt = orderChangeWaitCnt;
	}
	@Override
	public String toString() {
		return "PropertyInfo [period=" + period + ", activeYn=" + activeYn + ", minMargin=" + minMargin
				+ ", minBuyMargin=" + minBuyMargin + ", minSellMargin=" + minSellMargin + ", orderConfirmPeriod="
				+ orderConfirmPeriod + ", orderChangeCnt=" + orderChangeCnt + ", orderChangeWaitCnt="
				+ orderChangeWaitCnt + "]";
	}
	
}
