package by.itacademy.training.yoshop.service;

import java.util.List;

import by.itacademy.training.yoshop.entity.BlackListRecord;
import by.itacademy.training.yoshop.entity.Category;
import by.itacademy.training.yoshop.entity.Model;
import by.itacademy.training.yoshop.entity.User;

public interface IYoShopDataServiceProvider {

	public boolean checkUserByLogin(String login);

	public boolean checkUserByEmail(String email);

	public User getUser(String login);

	public boolean addUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);

	public List<User> userList();

	public List<User> blackList();

	public User loginUser(String login, String password);

	public List<BlackListRecord> getBanList();

	public boolean addUserToBanList(int id, int days);

	public boolean stopBanUser(int id);

	public List<BlackListRecord> UserBanListStory(User user);

	public boolean addModel(Model model);

	public boolean updateModel(Model model);

	public boolean deleteModel(int modelId);

	public Model getModel(int modelId);

	public List<Model> getModels(int categoryId);

	public List<Model> getModels(int categoryId, int from, int pageSize);

	public boolean addCategory(Category category);

	public boolean updateCategory(Category category);

	public boolean deleteCategory(int categoryId);

	public Category getCategory(int categoryId);

	public List<Category> rootCategories();

	public List<Category> pathToRoot(int categoryId);

	public List<Category> getSubCategories(int categoryId);

	public List<Category> getAllSubCategories(int categoryId);

	public int countModels(int categoryId);

}
