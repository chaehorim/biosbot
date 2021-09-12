package com.chochae.afes.user.dao;

import java.util.List;

import com.chochae.afes.user.model.UserInfo;

public interface UserDAO {
	
	public boolean userExistYn(String userId);
	
	public boolean insertUser(UserInfo user);
	
	public List<UserInfo> selectUserList(String userId);
	
	public List<UserInfo> selectUserListByParentId(String userId);
	
	public List<UserInfo> selectUserListByType(String ...type);
	
	public boolean updateUser(UserInfo user);
	
	public boolean updateUserByAdmin(UserInfo user);
	
	public boolean updateOperatorAmount(UserInfo user);
	
	//set manager to operators.
	public boolean updateManagerOperator(String parentId, String...userId);
	
	//set manager amount;
	public boolean updateManagerAmount(List<String> userIdList, List<Float> amountList);
	
	public boolean deleteUser(String userId);
	
}
