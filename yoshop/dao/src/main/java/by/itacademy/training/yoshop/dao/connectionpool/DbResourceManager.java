package by.itacademy.training.yoshop.dao.connectionpool;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import by.itacademy.training.yoshop.dao.exceptions.DbResourceManagerException;

public class DbResourceManager {

	private static DbResourceManager instance;
	private final static String RESOURCE_FILE_POSITION = "db";

	private static ResourceBundle bundle;

	public static DbResourceManager getInstance() throws DbResourceManagerException {
		if (instance == null) {
			instance = new DbResourceManager();
			try {
				bundle = ResourceBundle.getBundle(RESOURCE_FILE_POSITION);
			} catch (MissingResourceException e) {
				throw new DbResourceManagerException("Missing db.property file.", e);
			}
		}
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}