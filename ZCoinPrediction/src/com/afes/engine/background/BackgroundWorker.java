package com.afes.engine.background;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BackgroundWorker implements Runnable {

	private static Logger logger = LogManager.getLogger(BackgroundWorker.class);
	
	Thread backgroundThread;
    
    private boolean isRunning;
    private int interval = 10000; // ms
	
	List<String> jobList = new ArrayList<>();
    
	public void init() {
		jobList.add("huobi");
		jobList.add("okcoin");
	}
    
    public void start(){
    	backgroundThread = new Thread(this, "BackgroundWorker");
        this.isRunning = true;
        backgroundThread.setDaemon(true);
        backgroundThread.start();
        logger.info("backgroundThread start");
        
    }
    
    public void stop(){
        this.isRunning = false;
        backgroundThread.interrupt();
        logger.info("backgroundThread stop");
    }
	
    public void setJobList(List<String> jobList) {
    	this.jobList = jobList; 
    }
    
    @Override
    public void run() {
        while(this.isRunning){
            long prev = System.currentTimeMillis();
            
            work();
            
            long now = System.currentTimeMillis();
            try {
                long sleepTime = interval - now + prev;
                if(sleepTime > 0){
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
            }
        }
    }

	private void work() {
		InitialContext cxt;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cxt = new InitialContext();
			DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/AfesDB" );
			if ( ds == null ) {
				throw new Exception("Data source not found!");
			}
			connection = ds.getConnection();
			ps = connection.prepareStatement("select user from user");
			rs = ps.executeQuery();
			while(rs.next()) {
				String user = rs.getString("user");
				logger.info(user);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("================working=================");
		for(int i = 0, size = jobList.size(); i < size; i++) {
			String job = jobList.get(i);
			switch(job) {
				case "huobi":
					break;
				case "okcoin":
					break;
			}
		}
	}
	
	
	
	
	
}
