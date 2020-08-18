package tp.p3.control.commands;

import tp.p3.exceptions.CommandParserException;
import tp.p3.logic.Game;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandInfo, String commandFirstLetter, String helpInfo){
		super(commandInfo,commandFirstLetter, helpInfo);
	}
	
	public abstract boolean execute(Game game);
	
	public Command parse(String[] commandWords) throws CommandParserException {
		if(commandWords[0].equalsIgnoreCase(commandName))
			return this; 
		return null;
	}
}
