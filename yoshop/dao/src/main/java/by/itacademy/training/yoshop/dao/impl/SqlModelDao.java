package by.itacademy.training.yoshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.training.yoshop.dao.IModelDao;
import by.itacademy.training.yoshop.dao.connectionpool.ConnectionPool;
import by.itacademy.training.yoshop.dao.exceptions.ConnectionPoolException;
import by.itacademy.training.yoshop.entity.Model;

public class SqlModelDao implements IModelDao {

	private static ConnectionPool connectionPool;
	private static SqlModelDao instance;

	private static final String ADD_MODEL_QUERY = "INSERT INTO models (id,imageId,price,warranty,title,description,delivery,availability,categoryId) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String GET_MODEL_QUERY = "SELECT * FROM models WHERE id = ?";
	private static final String GET_MODELS_QUERY = "SELECT * FROM models WHERE categoryId = ? ORDER BY id";

	private SqlModelDao() {

	}

	public static SqlModelDao getInstance(ConnectionPool pool) {
		if (instance == null) {
			connectionPool = pool;
			instance = new SqlModelDao();
		}
		return instance;
	}

	@Override
	public boolean addModel(Model model) {

		int result = 0;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(ADD_MODEL_QUERY)) {
			pStmt.setInt(1, model.getId());
			pStmt.setInt(2, model.getImageId());
			pStmt.setDouble(3, model.getPrice());
			pStmt.setInt(4, model.getWarranty());
			pStmt.setString(5, model.getTitle());
			pStmt.setString(6, model.getDescription());
			pStmt.setString(7, model.getDelivery());
			pStmt.setString(8, model.getAvailability());
			pStmt.setInt(9, model.getCategoryId());
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
	public Model getModel(int modelId) {

		Model model = new Model();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(GET_MODEL_QUERY)) {
			pStmt.setInt(1, modelId);
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next() == true) {
					model = DataReaderEx.toModel(rs);
				} else {
					model = null;
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public List<Model> getModels(int categoryId) {

		List<Model> models = new ArrayList<Model>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement pStmt = connection.prepareStatement(GET_MODELS_QUERY)) {
			pStmt.setInt(1, categoryId);
			try (ResultSet rs = pStmt.executeQuery()) {
				while (rs.next()) {
					models.add(DataReaderEx.toModel(rs));
				}
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return models;
	}

	@Override
	public List<Model> getModels(int categoryId, int from, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateModel(Model model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteModel(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}
}
