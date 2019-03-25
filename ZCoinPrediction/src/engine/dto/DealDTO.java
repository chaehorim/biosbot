package engine.dto;

import java.text.SimpleDateFormat;

public class DealDTO {
	private DealType type;
	private double 	price;
	private int betUnit;
	
	private double varA;
	private double minCost;
	private String comment;
	private FunctionResultInterface result;
	private long timestamp;
	SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
	public DealType getType() {
		return type;
	}
	public void setType(DealType type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getVarA() {
		return varA;
	}
	public void setVarA(double varA) {
		this.varA = varA;
	}
	public double getMinCost() {
		return minCost;
	}
	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public FunctionResultInterface getResult() {
		return result;
	}
	public void setResult(FunctionResultInterface result) {
		this.result = result;
	}
	public int getBetUnit() {
		return betUnit;
	}
	public void setBetUnit(int betUnit) {
		this.betUnit = betUnit;
	}
	
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		
		return "DealDTO [type=" + type + ", price=" + price + ", betUnit=" + betUnit + ", varA=" + varA + ", minCost="
				+ minCost + ", comment=" + comment + ", result=" + result + ", timestamp=" + time_formatter.format(timestamp) + "]";
	}
	
}
