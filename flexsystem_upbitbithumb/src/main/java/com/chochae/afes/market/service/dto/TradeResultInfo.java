package com.chochae.afes.market.service.dto;

public class TradeResultInfo {
	private boolean successYn;
	private String tradeId;
	private float  tradeVal;
	private double  finalPrice;
	
	public TradeResultInfo(boolean successYn, String tradeId) {
		super();
		this.successYn = successYn;
		this.tradeId = tradeId;
	}
	public boolean isSuccessYn() {
		return successYn;
	}
	public void setSuccessYn(boolean successYn) {
		this.successYn = successYn;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public float getTradeVal() {
		return tradeVal;
	}
	public void setTradeVal(float tradeVal) {
		this.tradeVal = tradeVal;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	@Override
	public String toString() {
		return "TradeResultInfo [successYn=" + successYn + ", tradeId=" + tradeId + ", tradeVal=" + tradeVal
				+ ", finalPrice=" + finalPrice + "]";
	}
}
