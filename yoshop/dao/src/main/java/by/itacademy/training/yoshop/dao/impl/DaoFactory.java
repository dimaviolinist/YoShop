package by.itacademy.training.yoshop.dao.impl;

import by.itacademy.training.yoshop.dao.IBanManagementDao;
import by.itacademy.training.yoshop.dao.ICategoryDao;
import by.itacademy.training.yoshop.dao.IDaoFactory;
import by.itacademy.training.yoshop.dao.IModelDao;
import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;

public class DaoFactory implements IDaoFactory {

	private static DaoFactory instance;
	private ICategoryDao categoryDao;
	private IModelDao modelDao;
	private IUserDao userDao;
	private IBanManagementDao banManagementDao;

	private DaoFactory() {

	}

	public static DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}

	public void initDaoFactory(ConnectionPool connectionPool) {
		categoryDao = SqlCategoryDao.getInstance(connectionPool);
		modelDao = SqlModelDao.getInstance(connectionPool);
		userDao = SqlUserDao.getInstance(connectionPool);
		banManagementDao = SqlBanManagementDao.getInstance(connectionPool);
	}

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	public IModelDao getModelDao() {
		return modelDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@Override
	public IBanManagementDao getManagementDao() {
		return banManagementDao;
	}

}
