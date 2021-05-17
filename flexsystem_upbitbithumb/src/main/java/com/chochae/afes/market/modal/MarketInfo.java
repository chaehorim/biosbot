/**
 * 
 */
package com.chochae.afes.market.modal;

import com.chochae.afes.common.constants.CurrencyConstants;
import com.chochae.afes.market.company.MarketInterface;

/**
 * @author Isco
 *
 */
public class MarketInfo {
	private String 	marketId;
	private String 	marketUserName;
	private int		currencyCode;
	private String 	currencyName;
	private String	url;
	private float	amount;
	private float	bcCnt;
	private String	activeYn;
	private String	publicKey;
	private String	privateKey;
	private String  marketAddress;
	private String	transferPassword;
	private boolean defaultYn;
	private boolean validateYn;
	private MarketInterface marketCon;
	private boolean fromYn;
	private boolean toYn;
		
	public MarketInfo() {
		super();
	}
	
	
	public MarketInfo(String marketId, String marketName, int currencyCode, String currencyName, String url,
			float amount, float bcCnt, String activeYn) {
		super();
		this.marketId = marketId;
		this.marketUserName = marketName;
		this.currencyCode = currencyCode;
		this.currencyName = currencyName;
		this.url = url;
		this.amount = amount;
		this.bcCnt = bcCnt;
		this.activeYn = activeYn;
	}


	public MarketInfo(String marketId, String marketName, String currencyName, String url, float amount, float bcCnt) {
		super();
		this.marketId = marketId;
		this.marketUserName = marketName;
		this.currencyCode = CurrencyConstants.getCode(currencyName)  ;
		this.currencyName = currencyName;
		this.url = url;
		this.amount = amount;
		this.bcCnt = bcCnt;
		this.activeYn = "Y";
	}

	public MarketInfo(String marketId, String marketName, int currencyCode, String url, float amount, float bcCnt) {
		super();
		this.marketId = marketId;
		this.marketUserName = marketName;
		this.currencyCode = currencyCode;
		this.currencyName = CurrencyConstants.getCountry(currencyCode);
		this.url = url;
		this.amount = amount;
		this.bcCnt = bcCnt;
		this.activeYn = "N";
	}


	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getMarketUserName() {
		return marketUserName;
	}

	public void setMarketUserName(String marketUserName) {
		this.marketUserName = marketUserName;
	}
	public int getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	
	public String getPublicKey() {
		return publicKey;
	}


	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}


	public String getPrivateKey() {
		return privateKey;
	}


	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getMarketAddress() {
		return marketAddress;
	}


	public void setMarketAddress(String marketAddress) {
		this.marketAddress = marketAddress;
	}


	public String getTransferPassword() {
		return transferPassword;
	}


	public void setTransferPassword(String transferPassword) {
		this.transferPassword = transferPassword;
	}

	

	public boolean isDefaultYn() {
		return defaultYn;
	}


	public void setDefaultYn(boolean defaultYn) {
		this.defaultYn = defaultYn;
	}

	public boolean isValidateYn() {
		return validateYn;
	}

	public void setValidateYn(boolean validateYn) {
		this.validateYn = validateYn;
	}


	public MarketInterface getMarketCon() {
		return marketCon;
	}


	public void setMarketCon(MarketInterface marketCon) {
		this.marketCon = marketCon;
	}

	public boolean isFromYn() {
		return fromYn;
	}

	public void setFromYn(boolean fromYn) {
		this.fromYn = fromYn;
	}

	public boolean isToYn() {
		return toYn;
	}


	public void setToYn(boolean toYn) {
		this.toYn = toYn;
	}


	@Override
	public String toString() {
		return "MarketInfo [marketId=" + marketId + ", marketName=" + marketUserName + ", currencyCode=" + currencyCode
				+ ", currencyName=" + currencyName + ", url=" + url + ", amount=" + amount + ", bcCnt=" + bcCnt
				+ ", activeYn=" + activeYn + ", marketAddress=" + marketAddress + ", defaultYn=" + defaultYn + "]";
	}

}
