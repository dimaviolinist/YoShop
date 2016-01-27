package by.itacademy.training.yoshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.training.yoshop.dao.ICategoryDao;
import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;
import by.itacademy.training.yoshop.dao.exceptions.ConnectionPoolException;
import by.itacademy.training.yoshop.entity.Category;

public class SqlCategoryDao implements ICategoryDao {

	private static ConnectionPool connectionPool;
	private static SqlCategoryDao instance;

	private static final String ADD_CATEGORY_QUERY = "INSERT INTO categories (id,name,imageId,parentId) VALUES (?,?,?,?)";
	private static final String GET_CATEGORY_QUERY = "SELECT * FROM categories WHERE id = ?";
	private static final String ROOT_CATEGORIES_QUERY = "SELECT * FROM categories WHERE parentId = 0 ORDER BY id";
	private static final String GET_SUBSATEGORIES_QUERY = "SELECT * FROM categories WHERE parentId = ? ORDER BY id";

	private SqlCategoryDao() {

	}

	public static SqlCategoryDao getInstance(ConnectionPool pool) {
		if (instance == null) {
			connectionPool = pool;
			instance = new SqlCategoryDao();
		}
		return instance;
	}

	@Override
	public boolean addCategory(Category category) {

		int result = 0;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(ADD_CATEGORY_QUERY)) {
			pStmt.setInt(1, category.getId());
			pStmt.setString(2, category.getName());
			pStmt.setInt(3, category.getImageId());
			if (category.getParentId() == 0) {
				pStmt.setNull(4, java.sql.Types.INTEGER);
			} else {
				pStmt.setInt(4, category.getParentId());
			}
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
	public Category getCategory(int categoryId) {

		Category category = new Category();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(GET_CATEGORY_QUERY)) {
			pStmt.setInt(1, categoryId);
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next() == true) {
					category = DataReaderEx.toCategory(rs);
				} else {
					category = null;
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Category> rootCategories() {

		List<Category> categories = new ArrayList<Category>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(ROOT_CATEGORIES_QUERY)) {
			try (ResultSet rs = pStmt.executeQuery()) {
				while (rs.next()) {
					categories.add(DataReaderEx.toCategory(rs));
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public List<Category> getSubCategories(int categoryId) {

		List<Category> categories = new ArrayList<Category>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(GET_SUBSATEGORIES_QUERY)) {
			pStmt.setInt(1, categoryId);
			try (ResultSet rs = pStmt.executeQuery()) {
				while (rs.next()) {
					categories.add(DataReaderEx.toCategory(rs));
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public List<Category> getAllSubCategories(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countModels(int categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> pathToRoot(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
