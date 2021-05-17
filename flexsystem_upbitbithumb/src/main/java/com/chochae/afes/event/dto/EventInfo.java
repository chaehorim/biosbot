package com.chochae.afes.event.dto;

import java.util.List;

public class EventInfo {
	private int type;
	private int category;
	private String message;
	private List<Object> params;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Object> getParams() {
		return params;
	}
	public void setParams(List<Object> params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "EventInfo [type=" + type + ", category=" + category + ", message=" + message + "]";
	}
	
}
