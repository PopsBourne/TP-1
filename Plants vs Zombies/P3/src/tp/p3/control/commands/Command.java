package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParserException;
import tp.p3.exceptions.InvalidPatternException;
import tp.p3.exceptions.NoFileNameException;
import tp.p3.exceptions.SpaceNameException;
import tp.p3.logic.Game;

public abstract class Command {

	private String helpText;
	private String commandText;
	protected String commandName;
	protected String commandFirstLetter;

	public Command(String commandInfo, String commandFirstLetter, String helpInfo) {
		this.commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
		this.commandFirstLetter = commandFirstLetter;
	}

	public abstract boolean execute(Game game) throws CommandExecuteException, InvalidPatternException;

	public abstract Command parse(String[] commandWords)
			throws CommandParserException, NoFileNameException, SpaceNameException;

	public String helpText() {
		return " " +  helpText;
	}

}
