package by.itacademy.training.yoshop.listeners;

import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;

import by.itacademy.training.yoshop.entity.OnlineUsers;

public class SessionListener implements javax.servlet.http.HttpSessionListener {

	final static Logger logger = Logger.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

		logger.info("sessionCreated");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

		logger.info("sessionDestroyed");
		OnlineUsers.removeUser(arg0.getSession().getAttribute("user"));
	}

}
