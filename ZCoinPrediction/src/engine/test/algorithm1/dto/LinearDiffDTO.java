package engine.test.algorithm1.dto;

import engine.analysis.alogorithm1.Algorithm1Result;
import engine.dto.DealDTO;

public class LinearDiffDTO {
	private long startTime;
	private long endTime;
	private double startPrice;
	private double endPrice;
	private double diffMin;
	private double diffMax;
	private double startA;
	private double startAA;
	private double endA;
	private double endAA;
	
	public void init(DealDTO deal, long timestamp, double price) {
		this.startTime = timestamp;
		this.startPrice = price;
		this.diffMin = 10000;
		this.diffMax = -10000;
		this.startA = ((Algorithm1Result)deal.getResult()).getStandard().getA();
		this.startAA = ((Algorithm1Result)deal.getResult()).getLatest().getA();
	}

	public void update(DealDTO deal) {
		double diff =  ((Algorithm1Result)deal.getResult()).getLatest().getA()
				- ((Algorithm1Result)deal.getResult()).getStandard().getA();
		if (diff > this.diffMax) {
			this.diffMax = diff;
		}
		if (diff < this.diffMin) {
			this.diffMin = diff;
		}
	}
	
	public void finalizeDto(DealDTO deal, long timestamp, double price) {
		this.endTime = timestamp;
		this.endPrice = price;
		this.endA = ((Algorithm1Result)deal.getResult()).getStandard().getA();
		this.endAA = ((Algorithm1Result)deal.getResult()).getLatest().getA();
	}
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public double getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}
	public double getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}
	public double getDiffMin() {
		return diffMin;
	}
	public void setDiffMin(double diffMin) {
		this.diffMin = diffMin;
	}
	public double getDiffMax() {
		return diffMax;
	}
	public void setDiffMax(double diffMax) {
		this.diffMax = diffMax;
	}
	public double getStartA() {
		return startA;
	}
	public void setStartA(double startA) {
		this.startA = startA;
	}
	public double getStartAA() {
		return startAA;
	}
	public void setStartAA(double startAA) {
		this.startAA = startAA;
	}
	public double getEndA() {
		return endA;
	}
	public void setEndA(double endA) {
		this.endA = endA;
	}
	public double getEndAA() {
		return endAA;
	}
	public void setEndAA(double endAA) {
		this.endAA = endAA;
	}

	@Override
	public String toString() {
		return startTime + ":" + endTime + ":" + startPrice	+ ":" + endPrice + ":" + diffMin + ":" + diffMax + ":" + startA
				+ ":" + startAA + ":" + endA + ":" + endAA + ";";
	}
	
	
}
