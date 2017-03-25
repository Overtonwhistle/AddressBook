package service.impl;

import bean.Record;
import dao.RecordDAO;
import dao.exception.DAOException;
import dao.exception.DAOFileNotFoundException;
import dao.exception.DAOFileWriteErrorException;
import dao.factory.DAOFactory;
import dao.impl.AddressBook;
import service.AddressBookService;
import service.exception.ServiceException;
import service.exception.ServiceIncorrectDataException;
//import service.impl.DataCheking;

public class AddressBookServiceImpl implements AddressBookService {

	DAOFactory daoObjectFactory = DAOFactory.getIstance();
	RecordDAO recordDAO = daoObjectFactory.getRecordDAO();

	@Override
	public void addNewRecord(Record record) throws ServiceException, ServiceIncorrectDataException {
		if (!(record instanceof Record)) {
			throw new ServiceException("Service Layer: not a Record object passed to addNewRecord()");
		}

		String detectedErrors = DataCheking.recordDataCheck(record);

		try {
			if (detectedErrors == "") {
				try {
					recordDAO.addRecord(record);
				} catch (DAOFileWriteErrorException e) {
					throw new ServiceException("Service:file writr error", e);

				} catch (DAOFileNotFoundException e) {
					throw new ServiceException("Service:file writr error", e);
				}

			} else {
				throw new ServiceIncorrectDataException(detectedErrors);
			}

		} catch (DAOException e) {
			throw new ServiceException("Service Layer recieved error from recordDAO.addRecord() " + e);
		}
	}

	@Override
	public AddressBook getBook() throws ServiceException {
		Object bookForService = null;
		try {
			bookForService = recordDAO.getAddressBook();
		} catch (DAOFileNotFoundException e) {
			throw new ServiceException("File notfound from getBook()");
		} catch (DAOException e) {
			throw new ServiceException("DAO error from getBook()" + e);
		}

		if (bookForService instanceof AddressBook) {
			return (AddressBook) bookForService;
		} else {
			throw new ServiceException("Service Layer: DAO 'getBook' sent not an AddressBook object");
		}
	}

	@Override
	public Record getRecordByName(String name) throws ServiceException {
		if (name.trim().length() == 0) {
			throw new ServiceException("Service Layer: an incorrect value of Name passed to getRecordByName() ");
		}

		Record foundedRecord = null;

		try {
			foundedRecord = recordDAO.searchRecordByName(name);
		} catch (DAOException | DAOFileWriteErrorException | DAOFileNotFoundException e) {
			throw new ServiceException("Service Layer: error from getRecordByName() " + e);
		}

		if (foundedRecord instanceof Record) {
			return foundedRecord;
		} else {
			throw new ServiceException("Service Layer: DAO searchRecordByName() sent not an AddressBook object ");
		}

	}

	@Override
	public AddressBook getRecordsByAnyText(String text) throws ServiceException {
		if ((text == null) || (text.trim().length() == 0)) {
			throw new ServiceException("Service Layer: an incorrect value of Text passed to getRecordByAnyText() ");
		}

		AddressBook foundedRecords = null;

		try {
			foundedRecords = recordDAO.searchRecordForText(text);
		} catch (DAOException | DAOFileNotFoundException e) {
			throw new ServiceException("Service Layer: error from getRecordByName()");
		}
		if (foundedRecords instanceof AddressBook) {
			return foundedRecords;
		} else {
			throw new ServiceException("Service Layer: DAO searchRecordForText() sent not an AddressBook object");
		}
	}

	@Override
	public boolean deleteRecordByName(String name) throws ServiceException {
		if ((name == null) || (name.trim().length() == 0)) {
			throw new ServiceException("Service Layer: an incorrect value of Name passed to deleteRecordByName() ");
		}

		try {
			return recordDAO.deleteRecordForName(name);
		} catch (DAOException | DAOFileNotFoundException | DAOFileWriteErrorException e) {
			throw new ServiceException("Service Layer: error from deleteRecordFor() " + e);
		}
	}

	@Override
	public boolean editRecord(String name, Record newRecord) throws ServiceException, ServiceIncorrectDataException {
		boolean isDone = false;
		if ((name == null) || (name.trim().length() == 0)) {
			throw new ServiceException("Service Layer: an incorrect value of Name passed to deleteRecordByName() ");
		}

		if (!(newRecord instanceof Record)) {
			throw new ServiceException("Service Layer: not a Record object passed to addNewRecord()");
		}

		String detectedErrors = DataCheking.recordDataCheck(newRecord);

		if (detectedErrors == "") {
			try {
				isDone = recordDAO.editRecord(name, newRecord);
			} catch (DAOException | DAOFileWriteErrorException | DAOFileNotFoundException e) {
				throw new ServiceException("Service Layer: error from DAO editRecord() " + e);
			}
		} else {
			throw new ServiceException("Service Layer:" + detectedErrors);
		}

		return isDone;
	}

	@Override
	public AddressBook sortAddressBook() throws ServiceException {
		Object sorted = null;

		try {
			sorted = recordDAO.getSortedBook();
			if (sorted instanceof AddressBook) {
				return (AddressBook) sorted;
			} else {
				throw new ServiceException("Service Layer: not an AddressBook object returned to sortAddressBook()");
			}

		} catch (DAOException | DAOFileNotFoundException e) {
			throw new ServiceException("Service Layer: error from getSortedAddressBook()" + e);
		}

	}

	@Override
	public AddressBook getRecordsByNumber(String number) throws ServiceException {
		if ((number.trim().length() == 0) || (!number.substring(1).matches("[-+]?\\d+"))) {
			throw new ServiceException(
					"Service Layer: an incorrect value of Phone Number passed to getRecordByNumber()");
		}

		Object foundedRecord = null;
		try {
			foundedRecord = recordDAO.searchRecordForNumber(number);
			if (foundedRecord instanceof AddressBook) {
				return (AddressBook) foundedRecord;
			} else {
				throw new ServiceException("Service Layer: not an AddressBook object returned to sortAddressBook()");
			}
		} catch (DAOException | DAOFileNotFoundException e) {
			throw new ServiceException("Service Layer: error from DAO getRecordByName()" + e);
		}
	}

	@Override
	public boolean isBaseExist() throws ServiceException {
		try {
			return recordDAO.isBookFileExist();
		} catch (DAOException e) {
			throw new ServiceException("Service Layer: error from DAO isBookFileExist()" + e);
		}
	}

}
