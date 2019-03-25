package com.afes.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum AfesSingleton {
	INSTANCE;
	private static Logger logger = LogManager.getLogger(AfesSingleton.class);
	public void init() {
		logger.info("singleton ============");
	
		logger.info("================working=================");
	}
}
