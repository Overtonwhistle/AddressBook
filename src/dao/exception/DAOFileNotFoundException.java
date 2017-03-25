package dao.exception;

public class DAOFileNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOFileNotFoundException() {
		super();
	}

	public DAOFileNotFoundException(String message) {
		super(message);
	}

	public DAOFileNotFoundException(Exception e) {
		super(e);
	}

	public DAOFileNotFoundException(String message, Exception e) {
		super(message, e);
	}
}