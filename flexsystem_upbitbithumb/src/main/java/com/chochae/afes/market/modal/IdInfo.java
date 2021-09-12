package com.chochae.afes.market.modal;

public class IdInfo {

	private String userId;
	private String desc;
	private String privateKey;
	private String publicKey;
	private int	   currencyCode;
	private String marketId;
	private boolean defaultYn;
	public IdInfo(String userId, String desc, String publicKey, String privateKey, int currencyCode, String marketId) {
		super();
		this.userId = userId;
		this.desc = desc;
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		this.currencyCode = currencyCode;
		this.marketId = marketId;
		this.defaultYn = false;
	}
	
	public IdInfo(String userId, String desc, int currencyCode, String marketId, boolean defaultYn) {
		super();
		this.userId = userId;
		this.desc = desc;
		this.currencyCode = currencyCode;
		this.marketId = marketId;
		this.defaultYn = defaultYn;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public int getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public boolean isDefaultYn() {
		return defaultYn;
	}

	public void setDefaultYn(boolean defaultYn) {
		this.defaultYn = defaultYn;
	}

	@Override
	public String toString() {
		return "IdInfo [userId=" + userId + ", desc=" + desc + ", privateKey=" + privateKey + ", publicKey=" + publicKey
				+ ", currencyCode=" + currencyCode + ", marketId=" + marketId + ", defaultYn=" + defaultYn + "]";
	}

}
