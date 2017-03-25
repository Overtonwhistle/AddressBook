package service.factory;

import service.AddressBookService;
import service.impl.AddressBookServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final AddressBookService addressBookService = new AddressBookServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public AddressBookService getAddressBookService() {
		return addressBookService;
	}

	
	
}
