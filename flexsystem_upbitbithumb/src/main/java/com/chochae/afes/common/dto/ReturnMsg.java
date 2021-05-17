package com.chochae.afes.common.dto;

public class ReturnMsg {
	
	private boolean succesYn;
	private String	msg;
	
	public boolean isSuccesYn() {
		return succesYn;
	}
	public void setSuccesYn(boolean succesYn) {
		this.succesYn = succesYn;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
