package controller;

import java.util.HashMap;
import java.util.Map;

import command.Command;
import command.CommandName;
import command.impl.AddRecord;
import command.impl.DeleteName;
import command.impl.EditRecord;
import command.impl.FindName;
import command.impl.FindNumber;
import command.impl.FindText;
import command.impl.HelpBook;
import command.impl.SortBook;
import command.impl.ViewBook;
import command.impl.WrongRequest;

final class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.ADDRECORD, new AddRecord());
		repository.put(CommandName.DELETENAME, new DeleteName());
		repository.put(CommandName.EDITNAME, new EditRecord());
		repository.put(CommandName.FINDNAME, new FindName());
		repository.put(CommandName.FINDNUMBER, new FindNumber());
		repository.put(CommandName.FINDTEXT, new FindText());
		repository.put(CommandName.HELP, new HelpBook());
		repository.put(CommandName.VIEWBOOK, new ViewBook());
		repository.put(CommandName.SORTBOOK, new SortBook());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		
	}

	Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONG_REQUEST);
		}

		return command;
	}

}
