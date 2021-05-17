package com.chochae.afes.offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chochae.afes.offer.dto.OfferInfo;
import com.chochae.afes.offer.dto.OutterOfferInfo;

public class OfferManager {
	public final static int			MAX_LIST_SIZE =		100;
	private static final Logger logger = LoggerFactory.getLogger(OfferManager.class);
	private static Map<String, List<OfferInfo>> offerDatas = new HashMap<String, List<OfferInfo>>();            // key user Id
	private static List<OutterOfferInfo>     outterCurrencyList = new ArrayList<OutterOfferInfo>();               
	
	
    public static void addOffers(String userId, OfferInfo offer) {
    	if (!offerDatas.containsKey(userId)) {
    		offerDatas.put(userId, new ArrayList<OfferInfo>());
    	}
    	logger.info("[ADDED OFFER]" + offer);
    	List<OfferInfo> offerList = offerDatas.get(userId);
    	if (offerList.size() > MAX_LIST_SIZE) {
    		offerList.remove(0);
    	}
    	
    	offerDatas.get(userId).add(offer);
    	
    }
    
    public static List<OfferInfo> getOfferList(String userId, int fromCur, int toCur) {
    	List<OfferInfo> resultList = new ArrayList<OfferInfo>();
    	if (offerDatas.containsKey(userId)) {
    		List<OfferInfo> offerList = offerDatas.get(userId);
    		synchronized (offerList) {
    			for (int i = offerList.size() - 1; i >= 0; i --) {
    				OfferInfo offer = offerList.get(i);
    				if (offer.getFromLocale() == fromCur && offer.getToLocale() == toCur) {
    					resultList.add(offer);
    				}
    			}
    		}
    		return resultList;
    	}
    	return resultList;
    }
    
    public static void resetOuterOffer(List<OutterOfferInfo> outerOfferList) {
    	outterCurrencyList = outerOfferList;
    }
    
    public static List<OutterOfferInfo> getOutterOffer() {
    	return outterCurrencyList;
    }
}
