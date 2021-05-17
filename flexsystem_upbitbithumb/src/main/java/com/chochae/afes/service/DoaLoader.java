/**
 * 
 */
package com.chochae.afes.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chochae.afes.currency.dao.CurrencyDAO;
import com.chochae.afes.market.dao.MarketDAO;
import com.chochae.afes.properties.dao.PropertiesDAO;
import com.chochae.afes.user.dao.UserDAO;

/**
 * @author Isco
 *
 */
public class DoaLoader {
	private static ApplicationContext context = null;

	public static void init() {
    	getContext();
    }
    
    private static void getContext() {
    	if (context == null) 
    		context = new ClassPathXmlApplicationContext("Spring-Module.xml");	
    }
    
	
    public static UserDAO getUserDbConnection() {
    	getContext();
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    	return userDAO;
    }
    
    public static MarketDAO getMarketDBConnection() {
    	getContext();
    	MarketDAO marketDAO = (MarketDAO) context.getBean("marketDAO");
      	return marketDAO;
    }
    
    public static PropertiesDAO getPropertyDBConnection() {
    	getContext();
    	return (PropertiesDAO)context.getBean("propertyDAO");
    }
    
    public static CurrencyDAO getCurrencyDBConnection() {
    	getContext();
    	return (CurrencyDAO)context.getBean("currencyDAO");
    }
}
