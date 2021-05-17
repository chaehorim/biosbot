package com.chochae.afes.service.trigger;

public class AfesEngineTrigger {
	private static boolean processYn = true;
	
	public static boolean prevTrigger() {
		return processYn;
//		if (RunningTimeTrigger.isRunningTime())
//			return true;
//		return false;
	}
	
	public static boolean postTrigger() {
		
		return true;
	}
	
	public static boolean getHaltYn()  {
		return processYn;
	}
	
}
