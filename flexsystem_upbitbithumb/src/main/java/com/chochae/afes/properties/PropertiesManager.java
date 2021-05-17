package com.chochae.afes.properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chochae.afes.properties.dao.PropertiesDAO;
import com.chochae.afes.properties.modal.PropertyInfo;
import com.chochae.afes.service.DbLoader;


public class PropertiesManager {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesManager.class);
	
    private static PropertyInfo prop;                                                                               		// 속성 정보들
    public static int initCnt = 0;
    
	public static int initProperties() throws Exception {
		readProperties();
		return initCnt;
	}
	
	private static void readProperties() throws Exception {
		PropertiesDAO propDAO = DbLoader.getPropertyDBConnection();
		prop = propDAO.selectPrperties();
	}
	
	public static PropertyInfo getProperties() {
		return prop;
	}

}
