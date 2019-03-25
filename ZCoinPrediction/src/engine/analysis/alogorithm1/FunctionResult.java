package engine.analysis.alogorithm1;

public class FunctionResult{
	private double a;
	private double b;
	private double minCost;
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public double getMinCost() {
		return minCost;
	}
	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}
	@Override
	public String toString() {
		return "functionResult [a=" + a + ", b=" + b + ", minCost=" + minCost + "]";
	}
}
