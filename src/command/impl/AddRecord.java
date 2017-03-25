package command.impl;

import bean.Record;
import command.Command;
import service.AddressBookService;
import service.exception.ServiceException;
import service.exception.ServiceIncorrectDataException;
import service.factory.ServiceFactory;

public class AddRecord implements Command {

	@Override
	public String execute(String text) {
		String response = "New record created.";
		Record newrecord;

		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		newrecord = Utils.inputNewRecord();

		try {
			addressBookService.addNewRecord(newrecord);
		} catch (ServiceException e) {
			response = "ADDRECORD error!";
		} catch (ServiceIncorrectDataException e) {
			response = "Incorrect data, record not created!";
		}

		return response;
	}

}
