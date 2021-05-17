package com.chochae.afes.offer.dto;

import java.util.List;
import java.util.Map;

import com.chochae.afes.record.dao.RecordInfo;
import com.chochae.afes.resource.dto.UserAssetInfo;

public class OfferUserData {
	private List<OrderInfo> orderList;
	private List<UserAssetInfo> userSettingList;
	private OfferInfo  recentOffer;
	private List<OutterOfferInfo> outterOfferList;
	private List<DealInfo> offerList;
	private List<DealInfo> dealList;
	private Map<String, Double[]>  coinAssetMap;
	private List<RecordInfo>   logList;
	private long		curTime;
	
	public OfferUserData() {
		super();
		curTime = System.currentTimeMillis();
	}
	public List<OrderInfo> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderInfo> orderList) {
		this.orderList = orderList;
	}
	public List<UserAssetInfo> getUserSettingList() {
		return userSettingList;
	}
	public void setUserSettingList(List<UserAssetInfo> userSettingList) {
		this.userSettingList = userSettingList;
	}
	public OfferInfo getRecentOffer() {
		return recentOffer;
	}
	public void setRecentOffer(OfferInfo recentOffer) {
		this.recentOffer = recentOffer;
	}
	public List<OutterOfferInfo> getOutterOfferList() {
		return outterOfferList;
	}
	public void setOutterOfferList(List<OutterOfferInfo> outterOfferList) {
		this.outterOfferList = outterOfferList;
	}
	public long getCurTime() {
		return curTime;
	}
	public void setCurTime(long curTime) {
		this.curTime = curTime;
	}
	public List<DealInfo> getOfferList() {
		return offerList;
	}
	public void setOfferList(List<DealInfo> offerList) {
		this.offerList = offerList;
	}
	public List<DealInfo> getDealList() {
		return dealList;
	}
	public void setDealList(List<DealInfo> dealList) {
		this.dealList = dealList;
	}
	public Map<String, Double[]> getCoinAssetMap() {
		return coinAssetMap;
	}
	public void setCoinAssetMap(Map<String, Double[]> coinAssetMap) {
		this.coinAssetMap = coinAssetMap;
	}
	public List<RecordInfo> getLogList() {
		return logList;
	}
	public void setLogList(List<RecordInfo> logList) {
		this.logList = logList;
	}
	
}
