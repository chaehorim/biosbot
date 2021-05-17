package com.chochae.afes.properties.modal;

public class PropertyInfo {

	private int 		period;					// offer ���� �ֱ�
	private float		bidBitcoin;				// offer�� ��Ʈ���� �ŷ� ��
	private boolean 	activeYn;				// offer ���� YN
	private float		minMargin;				// offer minimum margin
	private float		minBuyMargin;			// order minimum Buying Margin
	private float		minSellMargin;			// order minimum selling Margin
	private int			orderConfirmPeriod;		// order Confirm period ���� Ȯ���ϴ� �ð� ��
	private int			orderChangeCnt;			// order change Cnt ��� Ȯ�ε� buy/sell �ȵǸ� order���� ����
	private int			orderChangeWaitCnt;		// order ����� ��ٸ��� ȸ��  orderChangeWaitCnt * orderConfirmPeriod �ð���ŭ ���� ���� ����
	
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
