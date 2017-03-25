package command.impl;

import command.Command;
import dao.exception.DAOFileNotFoundException;
import service.AddressBookService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class ViewBook implements Command {

	@Override
	public String execute(String request) {
		String response = "";

		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		if (Utils.isDataBaseNotExist()) {
			response = "Base is empty. Add new record to create base.";
			return response;
		}

		try {
			Utils.viewAddresBook(addressBookService.getBook());
		} catch (ServiceException e) {
			response = "VIEWBOOK error!\n" + e;
		} catch (DAOFileNotFoundException e) {
			response = "VIEWBOOK error! File not found!\n" + e;
		}

		return response;

	}
}