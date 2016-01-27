
package by.itacademy.training.yoshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.itacademy.training.yoshop.entity.Category;
import by.itacademy.training.yoshop.entity.Model;
import by.itacademy.training.yoshop.entity.User;

public class DataReaderEx {

	public static Category toCategory(ResultSet rs) throws SQLException {

		Category category = new Category();

		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		int temp = rs.getInt("parentId");
		category.setParentId(!rs.wasNull() ? temp : 0);
		category.setImageId(!(rs.getInt("imageId") == 0) ? rs.getInt("imageId") : 0);

		return category;
	}

	public static Model toModel(ResultSet rs) throws SQLException {

		Model model = new Model();

		model.setId(rs.getInt("id"));
		model.setImageId(rs.getInt("imageId"));
		model.setPrice(rs.getDouble("price"));
		model.setWarranty(rs.getInt("warranty"));
		model.setTitle(rs.getString("title"));
		model.setDescription(rs.getString("description"));
		model.setDelivery(rs.getString("delivery"));
		model.setAvailability(rs.getString("availability"));
		model.setCategoryId(rs.getInt("categoryId"));

		return model;
	}

	public static User toUser(ResultSet rs) throws SQLException {

		User user = new User();

		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword("");
		user.setName(!rs.getString("name").isEmpty() ? rs.getString("name") : "");
		user.setSurname(!rs.getString("surname").isEmpty() ? rs.getString("surname") : "");
		user.setEmail(rs.getString("email"));
		user.setRole(rs.getString("role"));
		user.setIsBannedUntil(rs.getTimestamp("banend"));
		return user;

	}

}
