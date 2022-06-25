package com.chochae.afes.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chochae.afes.asset.AssetManager;
import com.chochae.afes.currency.CurrencyManager;
import com.chochae.afes.user.UserManager;


@Service
public class AfesSchedular {

	private static final Logger logger = LoggerFactory.getLogger(AfesSchedular.class);
    private static int sequence = 0;
    
	public static void startScheduler(int time) {
		ScheduledJob job = new ScheduledJob();
	    Timer jobScheduler = new Timer();
	    try {
	    	jobScheduler.scheduleAtFixedRate(job, 0, 1000 * 2);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    OrderMonitor job2 = new OrderMonitor();
	    Timer orderTimer = new Timer();
	    try {
			Calendar date = Calendar.getInstance();
			date.set(Calendar.AM_PM, Calendar.AM);
			date.set(Calendar.HOUR, 5);
			date.set(Calendar.MINUTE, 40);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);
			  
			orderTimer.scheduleAtFixedRate(job2, date.getTime(), 1000 * 60 * 60 * 24); 
			 // 1000 * 60 * 60 * 24 * 7);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	      //jobScheduler.cancel();
	}
	
	public static int getSequence() {
		return sequence++;
	}
	public static void stopScheduler() {
		
	}
	
	public static void restartScheduler() {
		
	}
	
	static class ScheduledJob extends TimerTask {
	   
	   public void run() {
//	      System.out.println(new Date());
	      try {
	    	  int curSeq = getSequence();
		      AfesEngineManager.generateOffer(curSeq);
	      } catch(Exception e) {
	    	  e.printStackTrace();
		  }
	   }
	}
	
	static class OrderMonitor extends TimerTask {
		public void run() {
			try {
				logger.info("[INSERT]");
				AssetManager.insertAsset();
			} catch(Exception e) {
		    	  e.printStackTrace();
			}
		}
	}
}
