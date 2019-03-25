package engine.analysis.alogorithm1;

import engine.dto.FunctionResultInterface;

public class Algorithm1Result  implements FunctionResultInterface {
	private FunctionResult standard;
	private FunctionResult latest;
	public FunctionResult getStandard() {
		return standard;
	}
	public void setStandard(FunctionResult standard) {
		this.standard = standard;
	}
	public FunctionResult getLatest() {
		return latest;
	}
	public void setLatest(FunctionResult latest) {
		this.latest = latest;
	}
	
}
