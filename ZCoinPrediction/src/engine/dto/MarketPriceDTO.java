package engine.dto;

import java.text.SimpleDateFormat;

public class MarketPriceDTO {

	private double buyPrice;
	private double sellPrice;
	private double price;
	private long   timeStamp;
	SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
	public MarketPriceDTO( double price) {
		super();
		this.price = price;
	}
	
	public MarketPriceDTO() {
		super();
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
	public double getPrice() {
		return price;
	}
	public void setPrice() {
		this.price = (this.buyPrice + this.sellPrice) / 2;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "MarketPriceDTO [timeStamp=" + time_formatter.format(timeStamp) + ", sellPrice=" + sellPrice + ", price=" + price + ", time="
				+ timeStamp + "] + buyPrice=[" +  buyPrice+ "]";
	}
}
