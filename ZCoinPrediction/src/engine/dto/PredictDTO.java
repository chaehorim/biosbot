package engine.dto;

public class PredictDTO {
	private String coinType;
	private double successPerc;
	private double amount;
	private BetType type;
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public double getSuccessPerc() {
		return successPerc;
	}
	public void setSuccessPerc(double successPerc) {
		this.successPerc = successPerc;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public BetType getType() {
		return type;
	}
	public void setType(BetType type) {
		this.type = type;
	}
	
	
}
