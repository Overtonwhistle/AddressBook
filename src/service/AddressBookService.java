package service;

import bean.Record;
import dao.exception.DAOFileNotFoundException;
import dao.impl.AddressBook;
import service.exception.ServiceException;
import service.exception.ServiceIncorrectDataException;

public interface AddressBookService {

	boolean isBaseExist() throws ServiceException;

	void addNewRecord(Record record) throws ServiceException, ServiceIncorrectDataException;

	boolean editRecord(String name, Record newRecord) throws ServiceException, ServiceIncorrectDataException;

	AddressBook getBook() throws ServiceException, DAOFileNotFoundException;

	AddressBook sortAddressBook() throws ServiceException;

	Record getRecordByName(String name) throws ServiceException;

	AddressBook getRecordsByNumber(String name) throws ServiceException;

	AddressBook getRecordsByAnyText(String name) throws ServiceException;

	boolean deleteRecordByName(String name) throws ServiceException;

}
