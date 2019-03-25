package com.afes.common.dto;

public class Market {

    private String marketid;
    
    private User marketname;
    private String url;
    private boolean useYn;
	public String getMarketid() {
		return marketid;
	}
	public void setMarketid(String marketid) {
		this.marketid = marketid;
	}
	public User getMarketname() {
		return marketname;
	}
	public void setMarketname(User marketname) {
		this.marketname = marketname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isUseYn() {
		return useYn;
	}
	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
	}

}
