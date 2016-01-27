package by.itacademy.training.yoshop.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.training.yoshop.dao.IBanManagementDao;
import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;
import by.itacademy.training.yoshop.dao.exceptions.ConnectionPoolException;
import by.itacademy.training.yoshop.entity.BlackListRecord;
import by.itacademy.training.yoshop.entity.User;

public class SqlBanManagementDao implements IBanManagementDao {

	private static ConnectionPool connectionPool;
	private static SqlBanManagementDao instance;

	private static final String GET_BAN_LIST_QUERY = "SELECT banstart, banend, reason FROM blacklist WHERE iduser=? ORDER BY banstart DESC";
	private static final String ADD_USER_TO_BAN_QUERY = "{call add_user_to_ban_list_by_id(?, ?)}";
	private static final String STOP_BAN_USER_QUERY = "{call stop_ban_user_id (?)}";

	private SqlBanManagementDao() {

	}

	public static SqlBanManagementDao getInstance(ConnectionPool pool) {
		if (instance == null) {
			connectionPool = pool;
			instance = new SqlBanManagementDao();
		}
		return instance;
	}

	@Override
	public List<BlackListRecord> getBanList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserToBanList(int id, int days) {
		int result = 0;
		try (Connection connection = connectionPool.takeConnection();
				CallableStatement cStmt = connection.prepareCall(ADD_USER_TO_BAN_QUERY)) {
			cStmt.setInt(1, id);
			cStmt.setInt(2, days);
			result = cStmt.executeUpdate();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<BlackListRecord> UserBanListStory(User user) {
		List<BlackListRecord> banListStory = new ArrayList<BlackListRecord>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(GET_BAN_LIST_QUERY)) {
			pStmt.setInt(1, user.getId());
			try (ResultSet rs = pStmt.executeQuery()) {
				while (rs.next()) {
					BlackListRecord blackListRecord = new BlackListRecord();
					blackListRecord.setBanStart(rs.getTimestamp("banstart"));
					blackListRecord.setBanEnd(rs.getTimestamp("banend"));
					blackListRecord.setReason(rs.getString("reason"));
					banListStory.add(blackListRecord);
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return banListStory;

	}

	@Override
	public boolean stopBanUser(int id) {

		int result = 0;
		try (Connection connection = connectionPool.takeConnection();
				CallableStatement cStmt = connection.prepareCall(STOP_BAN_USER_QUERY)) {
			cStmt.setInt(1, id);
			result = cStmt.executeUpdate();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

}
