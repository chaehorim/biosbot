package com.chochae.afes.record.dao;

import com.chochae.afes.record.MsgCode;

public class RecordInfo {
	private long timeStamp;
	private MsgCode messageCode;
	private String messageTxt;
	private String marketId;
	private String coinType;
	
	public RecordInfo(long timeStamp, MsgCode messageCode, String messageTxt, String marketId, String coinType) {
		super();
		this.timeStamp = timeStamp;
		this.messageCode = messageCode;
		this.messageTxt = messageTxt;
		this.marketId = marketId;
		this.coinType = coinType;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public MsgCode getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(MsgCode messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageTxt() {
		return messageTxt;
	}
	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	
	
}
