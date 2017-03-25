package command.impl;

import command.Command;

public class HelpBook implements Command {

	@Override
	public String execute(String request) {
		String helpString =
				  " ADDRECORD - creat new record in Address Book\n "
				+ "DELETENAME - delete record by Name\n "
				+ "EDITNAME - edit record by Name\n "
				+ "EXIT - exit program\n "
				+ "FINDNAME - find record(s) by Name\n "
				+ "FINDNUMBER - find record by Phone Number\n "
				+ "FINDTEXT - find record(s) by any text\n "
				+ "SORTBOOK - print Address Book sorted by Name\n "
				+ "VIEWBOOK - print all record in Address Book\n ";
				
		return helpString;
	}

}
