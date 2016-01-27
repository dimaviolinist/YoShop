package by.itacademy.training.yoshop.dao;

import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;

public interface IDaoFactory {

	public ICategoryDao getCategoryDao();

	public IModelDao getModelDao();

	public IUserDao getUserDao();

	public IBanManagementDao getManagementDao();

	public void initDaoFactory(ConnectionPool connectionPool);

}
