package com.chochae.afes.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chochae.afes.resource.ResourceManager;
import com.chochae.afes.service.DbLoader;
import com.chochae.afes.user.dao.UserDAO;
import com.chochae.afes.user.model.UserInfo;
import com.chochae.afes.user.model.UserSettingInfo;

@Service
public class UserManager {
	private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
	private static Map<String, UserInfo>			operatorMap				= new HashMap<String, UserInfo>();	   			// 사용자별 offer 당 제공 maxmum bitcoin 건수
    private static Map<String, UserInfo>			managerMap				= new HashMap<String, UserInfo>();	   			// 사용자별 offer 를 위해 manager 할당량 저장하기 위한 map
    private static Map<String, UserInfo>			userMap					= new HashMap<String, UserInfo>();	   			// 로그인 확인용
    private static Map<String, UserSettingInfo>     opSettingMap			= new HashMap<String, UserSettingInfo>();		// 오퍼레이터별 거래 설정.
    
	/* USER */
	public static void initUser() {
		managerMap.clear();
		operatorMap.clear();
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		List<UserInfo> operatorList = new ArrayList<UserInfo>();
		List<UserInfo> managerList = new ArrayList<UserInfo>();
		List<UserInfo> userList = userDAO.selectUserList(null);
		
		for (UserInfo user : userList) {
			if ("OPERATOR".equals(user.getUserType())) {
				operatorList.add(user);
			} else if ("MANAGER".equals(user.getUserType())) {
				managerList.add(user);
			}
			userMap.put(user.getUserId(), user);
		}
		
//		float bcCnt = ResourceManager.getTotalBcCnt();
		for (UserInfo user : managerList) {
			managerMap.put(user.getUserId(), user);
		}
		for (UserInfo user : operatorList) {
			operatorMap.put(user.getUserId(), user);
		}
		
		logger.info("[OPERATOR] MAP : " + operatorMap.size());
	}
	
	public static void editOperator() {
		initUser();
	}
	
	public static List<UserInfo> getOperatorList() {
		List<UserInfo> userList = new ArrayList<UserInfo>(operatorMap.values());
		return Collections.unmodifiableList(userList);
	}
	
	public static boolean checkUserAuth(String userId, String password) {
		UserInfo user = userMap.get(userId);
		if (user == null) {
			return false;
		}
		if (!password.equals(user.getPassword())) {
			return false;
		}
		return true;
	}
	
	public static String getUserType(String userId) {
		UserInfo user = userMap.get(userId);
		return user.getUserType();
	}
	
	// operator Trade Setting
	public static void addUserSetting(String userId, UserSettingInfo setting) {
		opSettingMap.put(userId, setting);
	}
	
	public static UserSettingInfo getUserSetting(String userId) {
		opSettingMap.putIfAbsent(userId, new UserSettingInfo(userId, false, 0.1f, "btc", 1100, 0.1f));
		return opSettingMap.get(userId);
	}
	
	public static void resetUserSetting() {
		opSettingMap.clear();
	}
}
