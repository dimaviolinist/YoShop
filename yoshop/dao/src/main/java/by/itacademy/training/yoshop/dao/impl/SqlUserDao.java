package by.itacademy.training.yoshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;
import by.itacademy.training.yoshop.dao.exceptions.ConnectionPoolException;
import by.itacademy.training.yoshop.entity.User;

public class SqlUserDao implements IUserDao {

	private static ConnectionPool connectionPool;
	private static SqlUserDao instance;

	private static final String CHECK_USER_BY_LOGIN_QUERY = "SELECT id FROM users WHERE isdeleted = 0 AND login = ?";
	private static final String CHECK_USER_BY_EMAI_LQUERY = "SELECT id FROM users WHERE isdeleted = 0 AND email = ?";

	// TODO add and role != 'admin' after all
	private static final String GET_USER_QUERY = "SELECT u.*,  banend FROM users u left join (SELECT iduser, MAX(banend) as banend FROM blacklist group by iduser) as b on u.id = b.iduser where login=? and isdeleted = 0";
	private static final String ADD_USER_QUERY = "INSERT INTO users (login,password,name,surname,email) VALUES (?,?,?,?,?)";

	// where id in (?,?,?,?,?) for multi delete
	private static final String DELETE_USER_QUERY = "UPDATE users SET isdeleted = 1 WHERE id = ?";
	private static final String USER_LIST_QUERY = "SELECT u.*, case when b.banend>now() then b.banend end banend FROM users u LEFT JOIN (SELECT iduser, MAX(banend) as banend FROM blacklist GROUP BY iduser) as b on u.id = b.iduser WHERE isdeleted = 0 and role != 'admin' ORDER BY u.id";

	private static final String LOGIN_USER_QUERY = "SELECT u.*, banend FROM users u left join (SELECT iduser, MAX(banend) as banend FROM blacklist group by iduser) as b on u.id = b.iduser where login=? and password = ? and isdeleted = 0";
	// private static final String LOGIN_USER_QUERY = "SELECT * FROM users WHERE
	// isdeleted = 0 AND login = ? AND password = ?";
	// private static final String IS_BANNED_QUERY = "SELECT MAX(banend) FROM
	// blacklist WHERE iduser = ?";
	private static final String USER_BLACKLIST_QUERY = "SELECT u.*, banend FROM users u LEFT JOIN (SELECT iduser, MAX(banend) as banend FROM blacklist GROUP BY iduser) as b on u.id = b.iduser WHERE isdeleted = 0 and role != 'admin' ORDER BY u.id";

	private SqlUserDao() {

	}

	public static SqlUserDao getInstance(ConnectionPool pool) {
		if (instance == null) {
			connectionPool = pool;
			instance = new SqlUserDao();
		}
		return instance;
	}

	@Override
	public boolean checkUserByLogin(String login) {

		boolean check = false;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(CHECK_USER_BY_LOGIN_QUERY)) {
			pStmt.setString(1, login);
			try (ResultSet rs = pStmt.executeQuery()) {
				check = rs.next();
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean checkUserByEmail(String email) {

		boolean check = false;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(CHECK_USER_BY_EMAI_LQUERY)) {
			pStmt.setString(1, email);
			try (ResultSet rs = pStmt.executeQuery()) {
				check = rs.next();
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public User getUser(String login) {

		User user = new User();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(GET_USER_QUERY)) {
			pStmt.setString(1, login);
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next() == true) {
					user = DataReaderEx.toUser(rs);
				} else {
					user = null;
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean addUser(User user) {

		int result = 0;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(ADD_USER_QUERY)) {
			pStmt.setString(1, user.getLogin());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getSurname());
			pStmt.setString(5, user.getEmail());
			result = pStmt.executeUpdate();
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
	public boolean deleteUser(User user) {

		int result = 0;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(DELETE_USER_QUERY)) {
			pStmt.setInt(1, user.getId());
			result = pStmt.executeUpdate();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> userList() {

		List<User> users = new ArrayList<User>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(USER_LIST_QUERY)) {
			try (ResultSet rs = pStmt.executeQuery()) {
				while (rs.next()) {
					users.add(DataReaderEx.toUser(rs));
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> blackList() {

		List<User> users = new ArrayList<User>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(USER_BLACKLIST_QUERY)) {
			try (ResultSet rs = pStmt.executeQuery()) {
				while (rs.next()) {
					users.add(DataReaderEx.toUser(rs));
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User loginUser(String login, String password) {

		User user = new User();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(LOGIN_USER_QUERY)) {
			pStmt.setString(1, login);
			pStmt.setString(2, password);
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next() == true) {
					user = DataReaderEx.toUser(rs);
				} else {
					user = null;
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * @Override public Date isBanned(User user) {
	 * 
	 * Date banEnd = new Date(); try (Connection connection =
	 * connectionPool.takeConnection(); PreparedStatement pStmt =
	 * connection.prepareStatement(IS_BANNED_QUERY)) { pStmt.setInt(1,
	 * user.getId()); try (ResultSet rs = pStmt.executeQuery()) { if
	 * (!rs.next()) { banEnd = null; } else { SimpleDateFormat df = new
	 * SimpleDateFormat( "yyyy.MM.dd HH:mm:ss"); Date banend =
	 * df.parse(rs.getString("banend")); Date now = new Date(); if
	 * (now.before(banend)) { banEnd = banend; } else { banEnd = null; } } }
	 * catch (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } } catch (ConnectionPoolException e) {
	 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return banEnd; }
	 */

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
