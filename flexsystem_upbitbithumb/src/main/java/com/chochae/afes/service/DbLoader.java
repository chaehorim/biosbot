/**
 * 
 */
package com.chochae.afes.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.chochae.afes.asset.dao.AssetDAO;
import com.chochae.afes.coins.dao.CoinDAO;
import com.chochae.afes.currency.dao.CurrencyDAO;
import com.chochae.afes.market.dao.MarketDAO;
import com.chochae.afes.offer.dao.OfferDAO;
import com.chochae.afes.properties.dao.PropertiesDAO;
import com.chochae.afes.user.dao.UserDAO;

/**
 * @author Isco
 *
 */
@Service("dbloader")
public class DbLoader {
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
    
    public static OfferDAO getOfferDBConnection() {
    	getContext();
    	return (OfferDAO)context.getBean("offerDAO");
    }
    
    public static CoinDAO getCoinDBConnection() {
    	getContext();
    	return (CoinDAO)context.getBean("coinDAO");
    }
    
    public static AssetDAO getAssetDBConnection() {
    	getContext();
    	return (AssetDAO)context.getBean("assetDAO");
    }
}
