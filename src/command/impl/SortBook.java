package command.impl;

import command.Command;
import service.AddressBookService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class SortBook implements Command {

	@Override
	public String execute(String text) {
		String response = "";

		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		if (Utils.isDataBaseNotExist()) {
			response = "Base is empty. Add new record to create base.";
			return response;
		}

		try {
			Utils.viewAddresBook(addressBookService.sortAddressBook());
			response = "Address Book sorted by Name";
		} catch (ServiceException e) {
			response = "SORTBOOK error!\n" + e;
		}

		return response;
	}

}