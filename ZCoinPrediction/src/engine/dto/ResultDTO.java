package engine.dto;

public class ResultDTO {
	private String id;
	private String coinType;
	private String date;
	private double buyPrice;
	private double sellPrice;
	private double amount;
	private double successPerc;
	private OrderStatus staus;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getSuccessPerc() {
		return successPerc;
	}
	public void setSuccessPerc(double successPerc) {
		this.successPerc = successPerc;
	}
	public OrderStatus getStaus() {
		return staus;
	}
	public void setStaus(OrderStatus staus) {
		this.staus = staus;
	}
	@Override
	public String toString() {
		return "ResultDTO [id=" + id + ", coinType=" + coinType + ", date=" + date + ", buyPrice=" + buyPrice
				+ ", sellPrice=" + sellPrice + ", amount=" + amount + ", successPerc=" + successPerc + ", staus="
				+ staus + "]";
	}
	
}
