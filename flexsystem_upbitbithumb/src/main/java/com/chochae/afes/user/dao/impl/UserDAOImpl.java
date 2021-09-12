package com.chochae.afes.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.chochae.afes.user.dao.UserDAO;
import com.chochae.afes.user.model.UserInfo;

public class UserDAOImpl implements UserDAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean userExistYn(String userId) {
		String sql = "SELECT * FROM user WHERE USER_ID = ?";

		Connection conn = null;

		boolean userExist = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				userExist = true;
			}
			rs.close();
			ps.close();
			return userExist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public boolean insertUser(UserInfo user) {
		String sql = "INSERT INTO `user`(`USER_ID`,`USER_NAME`,`PHONE_NO`,`USER_TYPE`,`ADDRESS`,`LOCALE`,`EMAIL`,`OPERATE_AMOUNT`,`ACTIVE_YN`,`PARENT_ID`,`PASSWORD`) VALUES" +
						"(?, ?, ?, ?, ?, ? , ? ,?, ? ,?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, user.getUserId());
			ps.setString(i++, user.getUserName());
			ps.setString(i++, user.getPhoneNo());
			ps.setString(i++, user.getUserType());
			ps.setString(i++, user.getAddress());
			ps.setInt(i++, user.getLocale());
			ps.setString(i++, user.getEmail());
			ps.setFloat(i++, user.getAmount());
			ps.setString(i++, user.isActiveYn() ? "Y" : "N");
			ps.setString(i++, user.getParentId());
			ps.setString(i++, user.getPassword());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}

	@Override
	public boolean updateUser(UserInfo user) {
		String sql = "UPDATE user SET " +
					"	USER_NAME = ?, PASSWORD = ?, PHONE_NO =?,USER_TYPE = ?, ADDRESS = ?,LOCALE = ?, " +
					"	EMAIL = ?, OPERATE_AMOUNT = ?,ACTIVE_YN = ?, PARENT_ID = ?, TRADE_YN = ?, MIN_PROFIT =? TRADE_AMOUNT = ? " +
					"	WHERE USER_ID =? ";
	
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, user.getUserName());
			ps.setString(i++, user.getPassword());
			ps.setString(i++, user.getPhoneNo());
			ps.setString(i++, user.getUserType());
			ps.setString(i++, user.getAddress());
			ps.setInt(i++, user.getLocale());
			ps.setString(i++, user.getEmail());
			ps.setFloat(i++, user.getAmount());
			ps.setString(i++, "Y");
			ps.setString(i++, user.getParentId());
			ps.setString(i++, user.getUserId());
			ps.setString(i++, user.getTradeYn());
			ps.setFloat(i++, user.getMinProfit());
			ps.setFloat(i++, user.getTradeAmount());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}


	@Override
	public boolean updateOperatorAmount(UserInfo user) {
		String sql = "UPDATE user SET  USER_TYPE = ? WHERE USER_ID =? ";

		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserType());
			ps.setString(2, user.getUserId());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}

	@Override
	public boolean updateUserByAdmin(UserInfo user) {
		String sql = "UPDATE user SET USER_TYPE = ? WHERE USER_ID =? ";

		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, user.getUserType());
			ps.setString(i++, user.getUserId());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}
	
	@Override
	public boolean deleteUser(String userId) {
		String sql = "DELETE FROM user  WHERE USER_ID = ? ";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}

	@Override
	public List<UserInfo> selectUserList(String userId) {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		String sql = "SELECT * FROM user ";
		if (userId != null) {
			sql += " WHERE USER_ID = ?";
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			if (userId != null) {
				ps.setString(1, userId);
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				UserInfo user = new UserInfo();
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setPhoneNo(rs.getString("PHONE_NO"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setAddress(rs.getString("ADDRESS"));
				user.setLocale(rs.getInt("LOCALE"));
				user.setEmail(rs.getString("EMAIL"));
				user.setOperatorPercentage(rs.getFloat("OPERATE_AMOUNT"));
				user.setParentId(rs.getString("PARENT_ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setTradeYn(rs.getString("TRADE_YN"));
				user.setMinProfit(rs.getFloat("MIN_PROFIT"));
				user.setTradeAmount(rs.getFloat("TRADE_AMOUNT"));
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<UserInfo> selectUserListByParentId (String parentId) {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		String sql = "SELECT * FROM user WHERE PARENT_ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, parentId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				UserInfo user = new UserInfo();
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setPhoneNo(rs.getString("PHONE_NO"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setAddress(rs.getString("ADDRESS"));
				user.setLocale(rs.getInt("LOCALE"));
				user.setEmail(rs.getString("EMAIL"));
				user.setAmount(rs.getFloat("OPERATE_AMOUNT"));
				user.setParentId(rs.getString("PARENT_ID"));
//				user.setTradeYn(rs.getString("TRADE_YN"));
//				user.setMinProfit(rs.getFloat("MIN_PROFIT"));
//				user.setTradeAmount(rs.getFloat("TRADE_AMOUNT"));
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<UserInfo> selectUserListByType(String...types) {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		String sql = "SELECT * FROM user WHERE USER_TYPE IN (";
		
		for (int i = 0; i < types.length; i ++) {
			if (i != 0) {
				sql += ", ";
			}
			sql += " ?";
		}
		sql += ")";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			int cnt = 1;
			for (int i = 0; i < types.length; i ++) {
				ps.setString(cnt++, types[i]);
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				UserInfo user = new UserInfo();
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setPhoneNo(rs.getString("PHONE_NO"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setAddress(rs.getString("ADDRESS"));
				user.setLocale(rs.getInt("LOCALE"));
				user.setEmail(rs.getString("EMAIL"));
				user.setAmount(rs.getFloat("OPERATE_AMOUNT"));
				user.setParentId(rs.getString("PARENT_ID"));
//				user.setTradeYn(rs.getString("TRADE_YN"));
//				user.setMinProfit(rs.getFloat("MIN_PROFIT"));
//				user.setTradeAmount(rs.getFloat("TRADE_AMOUNT"));
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean updateManagerOperator(String parentId, String... userIds) {
		String sql = "UPDATE user SET PARENT_ID = ? WHERE USER_ID IN ( ";

		for (int i = 0; i < userIds.length; i ++) {
			if (i != 0) {
				sql += ", ";
			}
			sql += " ?";
		}
		sql += ")";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int cnt = 1;
			ps.setString(cnt++, parentId);
			for (int i = 0; i < userIds.length; i ++) {
				ps.setString(cnt++, userIds[i]);	
			}
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}

	@Override
	public boolean updateManagerAmount(List<String> userIdList, List<Float> amountList) {
		String sql = "UPDATE user SET  OPERATE_AMOUNT = ? WHERE USER_ID = ? ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < userIdList.size(); i ++) {
				int cnt = 1;
				ps.setFloat(cnt++, amountList.get(i));
				ps.setString(cnt++, userIdList.get(i));
				ps.addBatch();
//				ps.executeUpdate();
			}
			ps.executeBatch();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}
}
