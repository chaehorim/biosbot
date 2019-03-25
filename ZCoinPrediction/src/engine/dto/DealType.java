package engine.dto;

public enum DealType {
	STEADY,                // buy and sell meantime
	DOWN,                // first sell then buy
	RISE,                // first buy then sell
	NONE;				  // do Nothing

}
