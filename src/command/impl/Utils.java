package command.impl;

import java.util.Iterator;
import java.util.Scanner;

import bean.Record;
import dao.impl.AddressBook;
import service.AddressBookService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class Utils {

	public static void viewAddresBook(AddressBook book) {
		if (book.getBook().isEmpty()) {
			System.out.println("No data to view.");
		} else {
			System.out.println("-------------------");
			for (Iterator<Record> iterator = book.getBook().iterator(); iterator.hasNext();) {
				Record printedRecord = (Record) iterator.next();
				printRecord(printedRecord);
			}
			System.out.println("-------------------");
		}
	}

	public static void printRecord(Record record) {
		System.out.printf("%s %-20s", "Name:", record.getName());
		System.out.printf("%s %-25s", "address:", record.getAddress());
		System.out.printf("%s %-30s", "e-mail:", record.getEmail());
		System.out.printf("%s %-17s", "phone:", record.getPhoneNumber());
		System.out.printf("%s %-20s\n", "company:", record.getCompany());
	}

	public static Record inputNewRecord() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String name = "";
		String address = "";
		String email = "";
		String phoneNumber = "";
		String company = "";
		System.out.println("Creating new record. ");
		System.out.println("Enter name: ");
		do {
			name = sc.nextLine().toString().trim();
		} while (name.length() == 0);

		System.out.println("Enter address (press Enter for empty field): ");
		address = sc.nextLine().toString();
		if (address.trim().length() == 0) {
			address = "_empty_";
		}

		System.out.println("Enter e-mail (press Enter for empty field): ");
		email = sc.nextLine().toString();
		if (email.trim().length() == 0) {
			email = "_empty_";
		}

		System.out.println("Phone number (12 digits): ");
		do {
			phoneNumber = sc.nextLine().toString().trim();
		} while (phoneNumber.length() == 0);

		System.out.println("Enter company (press Enter for empty field): ");
		company = sc.nextLine().toString();
		if (company.trim().length() == 0) {
			company = "_empty_";
		}

		Record newRecord = new Record(name, address, email, "+" + phoneNumber, company);
		return newRecord;
	}

	public static Record EditRecord(Record oldRecord) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String name = "";
		String address = "";
		String email = "";
		String phoneNumber = "";
		String company = "";

		System.out.println("Editing record. ");

		System.out.println("Enter new name, or press Enter to keep old: ");
		name = sc.nextLine().toString().trim();
		if (name.length() == 0) {
			name = (oldRecord.getName());
		}

		System.out.println("Enter new address, or press Enter to keep old: ");
		address = sc.nextLine().toString().trim();
		if (address.length() == 0) {
			address = (oldRecord.getAddress());
		}

		System.out.println("Enter new e-mail, or press Enter to keep old value: ");
		email = sc.nextLine().toString().trim();
		if (email.length() == 0) {
			email = (oldRecord.getEmail());
		}

		System.out.println("Enter new phone number (12 digits), or press Enter to keep old value: ");
		phoneNumber = sc.nextLine().toString().trim();
		if (phoneNumber.length() == 0) {
			phoneNumber = (oldRecord.getPhoneNumber());
		}

		System.out.println("Enter new company, or press Enter to keep old value: ");
		company = sc.nextLine().toString().trim();
		if (company.length() == 0) {
			company = (oldRecord.getCompany());
		}

		Record newRecord = new Record(name, address, email, phoneNumber, company);
		return newRecord;
	}

	public static String inputString(String message) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String text = "";
		System.out.println(message);
		do {
			text = sc.nextLine().toString().trim();
		} while (text.length() == 0);
		return text;
	}

	public static boolean isDataBaseNotExist() {

		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		try {
			if (!addressBookService.isBaseExist()) {
				return true;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return false;

	}

}
