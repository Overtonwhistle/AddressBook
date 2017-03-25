package command.impl;

import bean.Record;
import command.Command;
import service.AddressBookService;
import service.exception.ServiceException;
import service.exception.ServiceIncorrectDataException;
import service.factory.ServiceFactory;

public class EditRecord implements Command {
	@Override

	public String execute(String request) {
		String response = "";

		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		if (Utils.isDataBaseNotExist()) {
			response = "Base is empty. Add new record to create base.";
			return response;
		}

		Record recordToEdit = null;
		String name = Utils.inputString("Enter name to edit record.");

		try {
			recordToEdit = addressBookService.getRecordByName(name);
		} catch (ServiceException e) {
			System.out.println("Error getting record for editing" + e);
			response = "EDIT error! " + e;
		}

		if (recordToEdit.getName() == "") {
			response = "Name '" + name + "' not found";
			return response;
		}
		System.out.println("-------------------");
		Utils.printRecord(recordToEdit);

		try {
			addressBookService.editRecord(name, Utils.EditRecord(recordToEdit));
			response = "Editing done!";
		}

		catch (ServiceIncorrectDataException e1) {
			response = "Incorrect data, record not edited!";
		}

		catch (ServiceException e) {
			response = "EDIT error! " + e;
		}

		return response;
	}

}
