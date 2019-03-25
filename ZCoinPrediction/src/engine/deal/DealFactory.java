package engine.deal;

import engine.dto.DealType;

public class DealFactory {

	public static DealInterface create(DealType type) {
		switch (type) {
		case STEADY:
			break;
		case DOWN:
			return new Deal2();
		case RISE:
			return new Deal3();
		case NONE:
			break;
		default:
			break;
		}
		
		return null;
	}
}
