package by.itacademy.training.yoshop.service.impl;

import java.util.List;

import by.itacademy.training.yoshop.dao.IBanManagementDao;
import by.itacademy.training.yoshop.dao.ICategoryDao;
import by.itacademy.training.yoshop.dao.IDaoFactory;
import by.itacademy.training.yoshop.dao.IModelDao;
import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;
import by.itacademy.training.yoshop.dao.exceptions.ConnectionPoolException;
import by.itacademy.training.yoshop.dao.exceptions.DbResourceManagerException;
import by.itacademy.training.yoshop.dao.impl.DaoFactory;
import by.itacademy.training.yoshop.entity.BlackListRecord;
import by.itacademy.training.yoshop.entity.Category;
import by.itacademy.training.yoshop.entity.Model;
import by.itacademy.training.yoshop.entity.User;
import by.itacademy.training.yoshop.service.IYoShopDataServiceProvider;

public class YoShopDataServiceProvider implements IYoShopDataServiceProvider {

	private static YoShopDataServiceProvider instance;
	private static ICategoryDao categoryDao;
	private static IModelDao modelDao;
	private static IBanManagementDao banManagementDao;
	private static IUserDao userDao;

	// TODO check for dao factory != null or check for it each method ?
	public static YoShopDataServiceProvider getInstance(IDaoFactory daoFactory) {
		if (instance == null) {
			instance = new YoShopDataServiceProvider();
			categoryDao = daoFactory.getCategoryDao();
			modelDao = daoFactory.getModelDao();
			banManagementDao = daoFactory.getManagementDao();
			userDao = daoFactory.getUserDao();
		}
		return instance;
	}

	public void serviceInit() {
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
	public boolean checkUserByLogin(String login) {
		return userDao.checkUserByLogin(login);
	}

	@Override
	public boolean checkUserByEmail(String email) {
		return userDao.checkUserByEmail(email);
	}

	@Override
	public User getUser(String login) {
		return userDao.getUser(login);
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		return userDao.deleteUser(user);
	}

	@Override
	public List<User> userList() {
		return userDao.userList();
	}

	@Override
	public List<User> blackList() {
		return userDao.blackList();
	}

	@Override
	public User loginUser(String login, String password) {
		return userDao.loginUser(login, password);
	}

	@Override
	public List<BlackListRecord> getBanList() {
		return banManagementDao.getBanList();
	}

	@Override
	public boolean addUserToBanList(int id, int days) {
		return banManagementDao.addUserToBanList(id, days);
	}

	@Override
	public boolean stopBanUser(int id) {
		return banManagementDao.stopBanUser(id);
	}

	@Override
	public List<BlackListRecord> UserBanListStory(User user) {
		return banManagementDao.UserBanListStory(user);
	}

	@Override
	public boolean addModel(Model model) {
		return modelDao.addModel(model);
	}

	@Override
	public boolean updateModel(Model model) {
		return modelDao.updateModel(model);
	}

	@Override
	public boolean deleteModel(int modelId) {
		return modelDao.deleteModel(modelId);
	}

	@Override
	public Model getModel(int modelId) {
		return modelDao.getModel(modelId);
	}

	@Override
	public List<Model> getModels(int categoryId) {
		return modelDao.getModels(categoryId);
	}

	@Override
	public List<Model> getModels(int categoryId, int from, int pageSize) {
		return modelDao.getModels(categoryId, from, pageSize);
	}

	@Override
	public boolean addCategory(Category category) {
		return categoryDao.addCategory(category);
	}

	@Override
	public boolean updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		return categoryDao.deleteCategory(categoryId);
	}

	@Override
	public Category getCategory(int categoryId) {
		return categoryDao.getCategory(categoryId);
	}

	@Override
	public List<Category> rootCategories() {
		return categoryDao.rootCategories();
	}

	@Override
	public List<Category> pathToRoot(int categoryId) {
		return categoryDao.pathToRoot(categoryId);
	}

	@Override
	public List<Category> getSubCategories(int categoryId) {
		return categoryDao.getSubCategories(categoryId);
	}

	@Override
	public List<Category> getAllSubCategories(int categoryId) {
		return categoryDao.getAllSubCategories(categoryId);
	}

	@Override
	public int countModels(int categoryId) {
		return categoryDao.countModels(categoryId);
	}

}
