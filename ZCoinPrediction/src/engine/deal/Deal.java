package engine.deal;

import engine.dto.OrderDTO;
import engine.dto.OrderStatus;

public abstract class Deal implements DealInterface{
	private int idx = 0;
	public OrderDTO sellOrder = new OrderDTO();
	public OrderDTO buyOrder = new OrderDTO();

	@Override
	public boolean isDealRunning() {
		if (sellOrder.getStatus() == OrderStatus.DONE && buyOrder.getStatus() == OrderStatus.DONE) {
			return false;
		}
		return true;
	}

	@Override
	public void increaseIdx() {
		idx++;		
	}

	@Override
	public int getIndex() {
		return idx;		
	}
	
	@Override
	public String saveToString() {
		// TODO Auto-generated method stub
		return null;
	}

}
