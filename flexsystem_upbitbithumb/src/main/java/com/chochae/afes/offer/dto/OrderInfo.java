/**
 * 
 */
package com.chochae.afes.offer.dto;

import java.util.ArrayList;
import java.util.List;

import com.chochae.afes.common.utils.TimeDateUtil;

/**
 * @author Isco
 *
 */
public class OrderInfo {

	private long 	startTime;
	private String	orderId;
	private int		fromLocale;
	private int		toLocale;
	private String  fromLocaleName;
	private String  toLocaleName;
	private float	buyAmout;
	private float   buyBcCnt;
	private float   sellAmount;
	private float   sellBcCnt;
	private float	buyCurrency;
	private float	sellCurrency;
	private float	profitPercentage;
	private float	profit;
	private int		status;
	private float		remainAmount;
	private float		remainBitCoin;
	private int		checkCount;
	private String	userId;
	private String	statDate;
	private String	coinType;
	private float outerExchangeRate;
	
	List<OrderDetailInfo>	orderDetailList;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public float getBuyCurrency() {
		return buyCurrency;
	}
	public void setBuyCurrency(float buyCurrency) {
		this.buyCurrency = buyCurrency;
	}
	public float getSellCurrency() {
		return sellCurrency;
	}
	public void setSellCurrency(float sellCurrency) {
		this.sellCurrency = sellCurrency;
	}
	public float getProfitPercentage() {
		return profitPercentage;
	}
	public void setProfitPercentage(float profitPercentage) {
		this.profitPercentage = profitPercentage;
	}
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<OrderDetailInfo> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetailInfo> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public int getFromLocale() {
		return fromLocale;
	}
	public void setFromLocale(int fromLocale) {
		this.fromLocale = fromLocale;
	}
	public int getToLocale() {
		return toLocale;
	}
	public void setToLocale(int toLocale) {
		this.toLocale = toLocale;
	}
	public String getFromLocaleName() {
		return fromLocaleName;
	}
	public void setFromLocaleName(String fromLocaleName) {
		this.fromLocaleName = fromLocaleName;
	}
	public String getToLocaleName() {
		return toLocaleName;
	}
	public void setToLocaleName(String toLocaleName) {
		this.toLocaleName = toLocaleName;
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
	public int getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(int checkCount) {
		this.checkCount = checkCount;
	}
	public void addCheckCount() {
		this.checkCount ++ ;
	}
	
	public float getBuyAmout() {
		return buyAmout;
	}
	public void setBuyAmout(float buyAmout) {
		this.buyAmout = buyAmout;
	}
	public float getBuyBcCnt() {
		return buyBcCnt;
	}
	public void setBuyBcCnt(float buyBcCnt) {
		this.buyBcCnt = buyBcCnt;
	}
	public float getSellAmount() {
		return sellAmount;
	}
	public void setSellAmount(float sellAmount) {
		this.sellAmount = sellAmount;
	}
	public float getSellBcCnt() {
		return sellBcCnt;
	}
	public void setSellBcCnt(float sellBcCnt) {
		this.sellBcCnt = sellBcCnt;
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
	
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	
	public float getOuterExchangeRate() {
		return outerExchangeRate;
	}
	public void setOuterExchangeRate(float outerExchangeRate) {
		this.outerExchangeRate = outerExchangeRate;
	}
	public void setOrderByOffer(OfferInfo offer, float buyVal, float sellVal) {
		long time = System.currentTimeMillis();
		this.startTime = time;
		this.statDate = TimeDateUtil.getStatDate(time);
		this.orderId = "ORDER_" + offer.getOfferId();
		this.fromLocale = offer.getFromLocale();
		this.toLocale = offer.getToLocale();
		this.fromLocaleName = offer.getFromLocaleName();
		this.toLocaleName = offer.getToLocaleName();
		this.buyAmout = offer.getBuyAmout();
		this.buyBcCnt = offer.getBuyBcCnt();
		this.sellAmount = offer.getSellAmount();
		this.sellBcCnt = offer.getSellBcCnt();
		this.buyCurrency = buyVal;
		this.sellCurrency = sellVal;
		this.profitPercentage = offer.getProfitPercentage();
		this.profit = offer.getProfit();
		this.status = 0;
		this.remainAmount = offer.getBuyAmout();
		this.remainBitCoin = offer.getSellBcCnt();
		this.checkCount = 0;
		this.userId = offer.getUserId();
		this.coinType = offer.getCoinType();
		this.outerExchangeRate = offer.getOutExchangeRate();
		List<OfferDetailInfo> offerDetail = offer.getDetailList();
		orderDetailList = new ArrayList<OrderDetailInfo>();
		
		for (OfferDetailInfo offerDet : offerDetail) {
			OrderDetailInfo orderDetail = new OrderDetailInfo(offerDet, this.orderId, this.startTime);
			orderDetailList.add(orderDetail);
		}
		
	}
	@Override
	public String toString() {
		return "OrderInfo [startTime=" + startTime + ", orderId=" + orderId + ", fromLocale=" + fromLocale
				+ ", toLocale=" + toLocale + ", fromLocaleName=" + fromLocaleName + ", toLocaleName=" + toLocaleName
				+ ", buyAmout=" + buyAmout + ", buyBcCnt=" + buyBcCnt + ", sellAmount=" + sellAmount + ", sellBcCnt="
				+ sellBcCnt + ", buyCurrency=" + buyCurrency + ", sellCurrency=" + sellCurrency + ", profitPercentage="
				+ profitPercentage + ", profit=" + profit + ", status=" + status + ", remainAmount=" + remainAmount
				+ ", remainBitCoin=" + remainBitCoin + ", checkCount=" + checkCount + ", userId=" + userId
				+ ", statDate=" + statDate + ", coinType=" + coinType + ", orderDetailList=" + orderDetailList + "]";
	}
}
