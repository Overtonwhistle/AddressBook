package command.impl;

import command.Command;
import service.AddressBookService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

public class DeleteName implements Command {

	@Override
	public String execute(String request) {
		String response = "";
		
		ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
		AddressBookService addressBookService = serviceObjectFactory.getAddressBookService();

		if (Utils.isDataBaseNotExist()) {
			response = "Base is empty. Add new record to create base.";
			return response;
		}
		
		String name = Utils.inputString("Enter name to delete");

		try {
			
			if (addressBookService.deleteRecordByName(name)) {
				response = "Name '"+name+"' deleted.";	
			} else 
				response = "Name '"+name+"' not found.";	
	
		} catch (ServiceException e) {
			response = "DELETENAME error!\n" + e;
		}
		return response;
	}

}
