package command.impl;

import bean.Record;
import command.Command;
import service.AddressBookService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class FindName implements Command {

	@Override
	public String execute(String request) {
		String response = "";
		
		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		if (Utils.isDataBaseNotExist()) {
			response = "Base is empty. Add new record to create base.";
			return response;
		}

		Record recordToView = null;
		String name = Utils.inputString("Enter name");

		try {
			recordToView = addressBookService.getRecordByName(name);
		} catch (ServiceException e) {
			response = "FINDNAME error!\n" + e;
			return response;
		}

		if (recordToView.getName() == "") {
			response = "Name '" + name + "' not found";
			return response;
		}

		Utils.printRecord(recordToView);

		return response;
	}

}
