package dao.factory;

import dao.RecordDAO;
import dao.impl.FileRecordDAO;

public final class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final RecordDAO fileRecordImpl = new FileRecordDAO();

	private DAOFactory() {
	}

	public static DAOFactory getIstance() {
		return instance;
	}

	public RecordDAO getRecordDAO() {
		return fileRecordImpl;
	}

}
