package by.itacademy.training.yoshop.dao;

import java.util.List;

import by.itacademy.training.yoshop.entity.User;

public interface IUserDao {

	// will be check login and email separately with AJAX

	public boolean checkUserByLogin(String login);

	public boolean checkUserByEmail(String email);

	public User getUser(String login);

	public boolean addUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);

	public List<User> userList();

	public List<User> blackList();

	public User loginUser(String login, String password);

	// public Date isBanned(User user);

}
