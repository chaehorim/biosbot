package com.afes.common.dto;

public class User{

    private String userid;
    private String name;
    private String phone;
    private String wechatid;
    private String password;

    public String getUserid() {
    	return userid;
    }
    
    public void setUserid(String userid) {
    	this.userid = userid;
    }
    
    public String getUsername() {
        return userid;
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

	public String getWechatid() {
		return wechatid;
	}

	public void setWechatid(String wechatid) {
		this.wechatid = wechatid;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
