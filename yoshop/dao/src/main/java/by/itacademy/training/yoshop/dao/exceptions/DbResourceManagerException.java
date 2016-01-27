package by.itacademy.training.yoshop.dao.exceptions;

public class DbResourceManagerException extends Exception {
	private static final long serialVersionUID = 1L;

	public DbResourceManagerException(String message, Exception e) {
		super(message, e);
	}

}
