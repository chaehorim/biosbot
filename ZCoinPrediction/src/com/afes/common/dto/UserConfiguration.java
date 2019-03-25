package com.afes.common.dto;

public class UserConfiguration {

    private String coinid;
    private User coinname;
    private String description;
    private boolean useYn;
    
	public String getCoinid() {
		return coinid;
	}
	public void setCoinid(String coinid) {
		this.coinid = coinid;
	}
	public User getCoinname() {
		return coinname;
	}
	public void setCoinname(User coinname) {
		this.coinname = coinname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isUseYn() {
		return useYn;
	}
	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
	}

}
