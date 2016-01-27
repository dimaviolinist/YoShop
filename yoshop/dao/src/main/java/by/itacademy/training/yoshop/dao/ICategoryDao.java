package by.itacademy.training.yoshop.dao;

import java.util.List;

import by.itacademy.training.yoshop.entity.Category;

public interface ICategoryDao {

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
