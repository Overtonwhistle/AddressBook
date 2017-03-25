package service.exception;

public class ServiceIncorrectDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceIncorrectDataException() {
		super();
	}

	public ServiceIncorrectDataException(String message) {
		super(message);
	}

	public ServiceIncorrectDataException(Exception e) {
		super(e);
	}

	public ServiceIncorrectDataException(String message, Exception e) {
		super(message, e);
	}

}