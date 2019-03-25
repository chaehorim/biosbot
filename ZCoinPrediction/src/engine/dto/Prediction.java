package engine.dto;

import java.text.SimpleDateFormat;

public class Prediction {
	private long index;
	private double buyPrice;
	private double sellPrice;
	private boolean buyYn;
	private boolean sellYn;
	private long timeStamp;
	private DealDTO deal;
	
	SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
	public Prediction() {
		super();
		this.buyYn = false;
		this.sellYn = false;
		this.index = 0;
	}
	public long getIndex() {
		return index;
	}
	public void setIndex(long index) {
		this.index = index;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public boolean isBuyYn() {
		return buyYn;
	}
	public void setBuyYn(boolean buyYn) {
		this.buyYn = buyYn;
	}
	public boolean isSellYn() {
		return sellYn;
	}
	public void setSellYn(boolean sellYn) {
		this.sellYn = sellYn;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public DealDTO getDeal() {
		return deal;
	}
	public void setDeal(DealDTO deal) {
		this.deal = deal;
	}
	
	public Prediction copyPrediction() {
		Prediction pred = new Prediction();
		pred.setBuyPrice(buyPrice);
		pred.setSellPrice(sellPrice);
		pred.setIndex(index);
		pred.setSellYn(sellYn);
		pred.setTimeStamp(timeStamp);
		DealDTO newDeal = new DealDTO();
		newDeal.setComment(deal.getComment());
		newDeal.setMinCost(deal.getMinCost());
		newDeal.setPrice(deal.getPrice());
		newDeal.setResult(deal.getResult());
		newDeal.setType(deal.getType());
		newDeal.setVarA(deal.getVarA());
		pred.setDeal(newDeal);
		
		return pred;
	}
	@Override
	public String toString() {
		return "Prediction [time=" + time_formatter.format(timeStamp * 1000) + ", comment=" + deal.getComment() + " buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + ", buyYn="
				+ buyYn + ", sellYn=" + sellYn + ", timeStamp=" + timeStamp + "], deal=" + deal ;
	}
}
