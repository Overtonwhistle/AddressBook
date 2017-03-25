package command.impl;

import command.Command;
import service.AddressBookService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class FindText implements Command {

	@Override
	public String execute(String request) {
		String response = "";
		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		if (Utils.isDataBaseNotExist()) {
			response = "Base is empty. Add new record to create base.";
			return response;
		}

		String text = Utils.inputString("Enter text for searching in all field of Records");

		try {
			Utils.viewAddresBook(addressBookService.getRecordsByAnyText(text));
		} catch (ServiceException e) {
			response = "FINDTEXT error!" + e;
		}
		return response;
	}

}