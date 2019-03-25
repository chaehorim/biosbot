package engine.dto;

public class OrderDTO {

	private String date;
	private String orderId;
	private double amount;
	private double price;
	private String coinType;
	private String orderType;
	private OrderStatus status;
	
	public OrderDTO() {
		super();
		this.status = OrderStatus.FAILED;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderDTO [date=" + date + ", orderId=" + orderId + ", amount=" + amount + ", price=" + price
				+ ", coinType=" + coinType + ", orderType=" + orderType + ", status=" + status + "]";
	}
}
