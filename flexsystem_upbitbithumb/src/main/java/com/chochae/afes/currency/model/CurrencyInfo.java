package com.chochae.afes.currency.model;

import com.chochae.afes.common.constants.CurrencyConstants;

public class CurrencyInfo {
	private	int			fromCode;			// 
	private int			toCode;
	private String		fromName;
	private String		toName;
	private float		value;				
	private long		time;				// update TimeStamp
	
	public CurrencyInfo() {
		super();
	}
	
	public CurrencyInfo(int fromCode, int toCode, float value, long time) {
		super();
		this.fromCode = fromCode;
		this.toCode = toCode;
		this.value = value;
		this.time = time;
		this.fromName = CurrencyConstants.getCountry(fromCode);
		this.toName = CurrencyConstants.getCountry(toCode);
	}

	public CurrencyInfo(int fromCode, int toCode, String fromName, String toName, float value, long time) {
		super();
		this.fromCode = fromCode;
		this.toCode = toCode;
		this.fromName = fromName;
		this.toName = toName;
		this.value = value;
		this.time = time;
	}
	public int getFromCode() {
		return fromCode;
	}
	public void setFromCode(int fromCode) {
		this.fromCode = fromCode;
	}
	public int getToCode() {
		return toCode;
	}
	public void setToCode(int toCode) {
		this.toCode = toCode;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "CurrencyInfo [fromCode=" + fromCode + ", toCode=" + toCode + ", fromName=" + fromName + ", toName="
				+ toName + ", value=" + value + ", time=" + time + "]";
	}
}
