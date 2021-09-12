/**
 * 
 */
package com.chochae.afes.offer.dto;

import com.chochae.afes.common.utils.TimeDateUtil;

import ch.qos.logback.core.util.TimeUtil;

/**
 * @author Isco
 *
 */
public class OrderDetailInfo {
	private String		oderId;
	private long		time;
	private String		detailId;
	private String		marketId;
	private String		marketName;
	private float		amount;
	private float		tradeRate;
	private float		bitcoinCnt;
	private int			tradeType; // 1 for buy, 2 for sale
	private int			status;    // 0 for success, 1 for fail, 2, loading, 3, failed success.
	private float		remainAmount;
	private float		remainBitCoin;
	private String		userId;
	private String		statDate;
	private String 		marketUserId;
	
	public OrderDetailInfo() {
		super();
	}
	
	public OrderDetailInfo(OfferDetailInfo offerDet, String orderId, long time) {
		super();
		this.oderId = orderId;
		this.time = time;
		this.detailId = offerDet.getMarketId() + "_" + orderId;
		this.marketId = offerDet.getMarketId();
		this.marketName = offerDet.getMarketName();
		this.marketUserId = offerDet.getMarketUserId();
		this.amount = offerDet.getAmount();
		this.tradeRate = offerDet.getTradeRate();
		this.bitcoinCnt = offerDet.getBitcoinCnt();
		this.tradeType = offerDet.getTradeType(); // 1 for buy, 2 for sale
		this.status = 2;    // 0 for success, 1 for fail, 2, loading, 3, failed success.
		this.remainAmount = (this.tradeType == 1 ? this.amount : 0);
		this.remainBitCoin = (this.tradeType == 2 ? this.bitcoinCnt : 0);
		this.statDate = TimeDateUtil.getStatDate(time);
	}
	
	public String getOderId() {
		return oderId;
	}
	public void setOderId(String oderId) {
		this.oderId = oderId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getTradeRate() {
		return tradeRate;
	}
	public void setTradeRate(float tradeRate) {
		this.tradeRate = tradeRate;
	}
	public float getBitcoinCnt() {
		return bitcoinCnt;
	}
	public void setBitcoinCnt(float bitcoinCnt) {
		this.bitcoinCnt = bitcoinCnt;
	}
	public int getTradeType() {
		return tradeType;
	}
	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public float getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(float remainAmount) {
		this.remainAmount = remainAmount;
	}
	public float getRemainBitCoin() {
		return remainBitCoin;
	}
	public void setRemainBitCoin(float remainBitCoin) {
		this.remainBitCoin = remainBitCoin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public String getMarketUserId() {
		return marketUserId;
	}

	public void setMarketUserId(String marketUserId) {
		this.marketUserId = marketUserId;
	}

	@Override
	public String toString() {
		return "OrderDetailInfo [oderId=" + oderId + ", time=" + time + ", detailId=" + detailId + ", marketId="
				+ marketId + ", marketName=" + marketName + ", amount=" + amount + ", tradeRate=" + tradeRate
				+ ", bitcoinCnt=" + bitcoinCnt + ", tradeType=" + tradeType + ", status=" + status + ", remainAmount="
				+ remainAmount + ", remainBitCoin=" + remainBitCoin + ", userId=" + userId + ", statDate=" + statDate
				+ ", marketUserId=" + marketUserId + "]";
	}


}
