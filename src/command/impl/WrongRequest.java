package command.impl;

import command.Command;

public class WrongRequest implements Command {

	@Override
	public String execute(String request) {
//	System.out.println("Wrong command! Try again.");
		return "Wrong command! (enter HELP to view command help)";
	}

}
