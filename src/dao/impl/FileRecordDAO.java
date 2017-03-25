package dao.impl;

import bean.Record;
import dao.RecordDAO;
import dao.exception.DAOException;
import dao.exception.DAOFileNotFoundException;
import dao.exception.DAOFileWriteErrorException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRecordDAO implements RecordDAO {
	public static final String BOOKFILE = "book.txt";
//	public static final String BOOKFILE = "D://a.txt";
	public static AddressBook myBook = new AddressBook();

	private static void saveBookToFile() throws DAOException, DAOFileWriteErrorException {

		ObjectOutputStream out = null;

		try {
			out = new ObjectOutputStream(new FileOutputStream(BOOKFILE));
		} catch (FileNotFoundException e) {
			throw new DAOFileWriteErrorException("Cannot write to file", e);
		} catch (IOException e) {
			throw new DAOException("IO Exception", e);
		}

		try {
			out.writeObject(myBook.getBook()); // ******** WRITE ArrayList Field
		} catch (IOException e) {
			throw new DAOException("IO Exception", e);
		} finally {

			try {
				out.close();
			} catch (IOException e) {
				throw new DAOException("IO Exception", e);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private static void readBookFromFile() throws DAOException, DAOFileNotFoundException {
		Object readObject;
		ObjectInputStream in = null;
		myBook.setBook(null);
		try {
			in = new ObjectInputStream(new FileInputStream(BOOKFILE));
		} catch (FileNotFoundException e) {
			throw new DAOFileNotFoundException("File not found", e);
		} catch (IOException e) {
			throw new DAOException("IO Exception", e);
		}

		try {
			readObject = in.readObject();
		} catch (ClassNotFoundException e) {
			throw new DAOException("Class ArrayList not found in file", e);
		} catch (IOException e) {
			throw new DAOException("IO Exception", e);
		} finally {

			try {
				in.close();
			} catch (IOException e) {
				throw new DAOException("IO Exception", e);
			}
		}

		if (readObject instanceof ArrayList) {
			myBook.setBook((ArrayList<Record>) readObject);

		} else {
			throw new DAOException("Not AddressBook Class in file");
		}
	}

	@Override
	public AddressBook getAddressBook() throws DAOException, DAOFileNotFoundException {
		readBookFromFile();
		return myBook;
	}

	@Override
	public void addRecord(Record record) throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException {
		myBook.getBook().add(record);
		saveBookToFile();
	}

	@Override
	public boolean editRecord(String name, Record newRecord)
			throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException {
		readBookFromFile();

		Record targetRecord = null;

		targetRecord = searchRecordByName(name);

		if (targetRecord != null) {
			targetRecord.setName(newRecord.getName());
			targetRecord.setAddress(newRecord.getAddress());
			targetRecord.setEmail(newRecord.getEmail());
			targetRecord.setPhoneNumber(newRecord.getPhoneNumber());
			targetRecord.setCompany(newRecord.getCompany());
			saveBookToFile();

			return true;

		} else {
			return false;
		}

	}

	@Override
	public void deleteRecord(Record record) throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException {
		readBookFromFile(); // ? to do or not to do?**************
		myBook.getBook().remove(record);
		saveBookToFile();
	}

	@Override
	public Record searchRecordByName(String nameToSearch)
			throws DAOException, DAOFileWriteErrorException, DAOFileNotFoundException {
		readBookFromFile();
		Record foundRecord = new Record();
		for (Iterator<Record> iterator = myBook.getBook().iterator(); iterator.hasNext();) {
			Record currentRecord = iterator.next();
			if (currentRecord.getName().equals(nameToSearch)) {
				foundRecord = currentRecord;
			}
		}
		return foundRecord;
	}

	@Override
	public AddressBook searchRecordForNumber(String numberToSearch) throws DAOException, DAOFileNotFoundException {
		readBookFromFile();
		AddressBook foundList = new AddressBook();
		for (Iterator<Record> iterator = myBook.getBook().iterator(); iterator.hasNext();) {
			Record currentRecord = iterator.next();
			if (currentRecord.getPhoneNumber().indexOf(numberToSearch) != -1) {
				foundList.addNewRecord(currentRecord);
			}
		}
		return foundList;
	}

	@Override
	public AddressBook searchRecordForText(String textToSearch) throws DAOException, DAOFileNotFoundException {
		AddressBook foundList = new AddressBook();
		readBookFromFile();
		Pattern searchPattern = Pattern.compile(textToSearch);

		for (Iterator<Record> iterator = myBook.getBook().iterator(); iterator.hasNext();) {
			Record currentRecord = iterator.next();

			Matcher mName = searchPattern.matcher(currentRecord.getName());
			Matcher mAddress = searchPattern.matcher(currentRecord.getAddress());
			Matcher mEmail = searchPattern.matcher(currentRecord.getEmail());
			Matcher mPhone = searchPattern.matcher(currentRecord.getPhoneNumber());
			Matcher mCompany = searchPattern.matcher(currentRecord.getCompany());

			if (mName.find() || mAddress.find() || mEmail.find() || mPhone.find() || mCompany.find()) {
				foundList.addNewRecord(currentRecord);
			}
		}
		return foundList;
	}

	@Override
	public boolean deleteRecordForName(String nameToDelete)
			throws DAOException, DAOFileNotFoundException, DAOFileWriteErrorException {
		readBookFromFile();

		for (Iterator<Record> iterator = myBook.getBook().iterator(); iterator.hasNext();) {
			Record currentRecord = iterator.next();
			if (currentRecord.getName().equals(nameToDelete)) {
				myBook.getBook().remove(currentRecord);
				saveBookToFile();
				return true;
			}
		}
		return false;
	}

	@Override
	public AddressBook getSortedBook() throws DAOException, DAOFileNotFoundException {
		readBookFromFile();
//		CompanyComparator a = new CompanyComparator();
		NameComparator b = new NameComparator();
		myBook.getBook().sort(b);
		return myBook;
	}

	@Override
	public boolean isBookFileExist() throws DAOException {
		File file = new File(BOOKFILE);
		if (file.exists() && file.isFile()) {
			return true;
		} else
			return false;
	}

}
