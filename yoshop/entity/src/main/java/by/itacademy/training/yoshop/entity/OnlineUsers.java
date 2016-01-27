package by.itacademy.training.yoshop.entity;

import java.util.HashSet;

public class OnlineUsers {

	private static HashSet<String> onlineUserList = new HashSet<String>();

	public static synchronized boolean addUser(String login) {
		return onlineUserList.add(login);
	}

	public static synchronized void removeUser(Object login) {
		onlineUserList.remove(login);
	}

}
