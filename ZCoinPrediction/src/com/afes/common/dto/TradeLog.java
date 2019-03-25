package com.afes.common.dto;

public class TradeLog {
    private String userid;
    private String name;
    private String phone;
    private String wechat;
    private String password;
    public String getUsername() {
        return userid;
    }

    public void setUsername(String userid) {
        this.userid = userid;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
