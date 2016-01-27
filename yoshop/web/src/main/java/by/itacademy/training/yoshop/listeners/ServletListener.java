package by.itacademy.training.yoshop.listeners;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;

import by.itacademy.training.yoshop.dao.IDaoFactory;
import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;
import by.itacademy.training.yoshop.dao.exceptions.ConnectionPoolException;
import by.itacademy.training.yoshop.dao.exceptions.DbResourceManagerException;
import by.itacademy.training.yoshop.dao.impl.DaoFactory;

public class ServletListener implements javax.servlet.ServletContextListener {

	final static Logger logger = Logger.getLogger(ServletListener.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		logger.info("ServletListener");

		ConnectionPool connectionPool = null;
		try {
			connectionPool = new ConnectionPool();
		} catch (DbResourceManagerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
		IDaoFactory factory = DaoFactory.getInstance();
		factory.initDaoFactory(connectionPool);

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}