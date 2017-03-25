package dao;

import bean.Record;
import dao.exception.DAOException;
import dao.exception.DAOFileNotFoundException;
import dao.exception.DAOFileWriteErrorException;
import dao.impl.AddressBook;

public interface RecordDAO {

	boolean isBookFileExist() throws DAOException;

	void addRecord(Record record) throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException;

	void deleteRecord(Record record) throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException;

	boolean deleteRecordForName(String nameToDelete) throws DAOException, DAOFileNotFoundException, DAOFileWriteErrorException;

	Record searchRecordByName(String nameToSearch) throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException;

	AddressBook searchRecordForText(String testToSearch) throws DAOException, DAOFileNotFoundException;

	AddressBook searchRecordForNumber(String numberToSearch) throws DAOException, DAOFileNotFoundException;

	AddressBook getAddressBook() throws DAOException, DAOFileNotFoundException;

	AddressBook getSortedBook() throws DAOException, DAOFileNotFoundException;

	boolean editRecord(String name, Record record) throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException;

}
