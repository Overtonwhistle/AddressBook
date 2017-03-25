package dao.exception;

public class DAOFileWriteErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOFileWriteErrorException() {
		super();
	}

	public DAOFileWriteErrorException(String message) {
		super(message);
	}

	public DAOFileWriteErrorException(Exception e) {
		super(e);
	}

	public DAOFileWriteErrorException(String message, Exception e) {
		super(message, e);
	}
}