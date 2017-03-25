package command.impl;

import command.Command;
import service.AddressBookService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class FindNumber implements Command {

	@Override
	public String execute(String request) {
		String response = "";
		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		if (Utils.isDataBaseNotExist()) {
			response = "Base is empty. Add new record to create base.";
			return response;
		}

		String number = Utils.inputString("Enter phone number (or part of number)");

		try {
			Utils.viewAddresBook(addressBookService.getRecordsByNumber(number));
		} catch (ServiceException e) {
			response = "FINDNUMBER error!" + e;
		}
		return response;
	}

}
