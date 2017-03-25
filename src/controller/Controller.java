package controller;

import command.Command;

public final class Controller {

	private final CommandProvider PROVIDER = new CommandProvider();
//	private final char PARAM_DELIMITER = ' ';

	public String executeTask(String request) {
		String commandName;
		Command executionCommand;
		
		commandName = request;//.substring(0, request.indexOf(PARAM_DELIMITER));
		executionCommand = PROVIDER.getCommand(commandName);
		String response;
		response = executionCommand.execute(request);
		return response;
	}
}





