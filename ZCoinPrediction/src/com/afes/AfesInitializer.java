package com.afes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.CoinDealer;


@WebListener
public class AfesInitializer implements ServletContextListener {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/afes";
	private static final String USER = "afes";
	private static final String PW = "afes1";
	private static Logger logger = LogManager.getLogger(AfesInitializer.class.getName());
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("===============init==================");
		CoinDealer.init();
	        
//		java.sql.PreparedStatement pstmt = null;
//		Connection conn = null;
//		ResultSet rs = null;
//        try {
//        	Class.forName(DRIVER);
//        	conn = (Connection) DriverManager.getConnection(URL, USER, PW);
//        	pstmt = conn.prepareStatement("SELECT * FROM afes.user");
//        	
//        	rs = pstmt.executeQuery();
//        	while (rs.next()) {
//        		String userId = rs.getString("USER_ID");
//        		logger.info("XXXX0" + userId);
//            }
//
//        } catch (SQLException se1) {
//            se1.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (pstmt != null)
//                	pstmt.close();
//            } catch (SQLException se2) {
//            }
//            try {
//                if (conn != null)
//                	conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }    
//





	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("================destroy=================");
	}

}
