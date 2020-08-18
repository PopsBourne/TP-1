package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParserException;
import tp.p3.exceptions.InvalidPatternException;
import tp.p3.exceptions.NoFileNameException;
import tp.p3.exceptions.SpaceNameException;
import tp.p3.logic.Game;

public abstract class FileCommands extends Command {

	protected String filenameString;

	public FileCommands(String commandInfo, String commandFirstLetter, String helpInfo) {
		super(commandInfo,commandFirstLetter, helpInfo);
	}

	@Override
	public abstract boolean execute(Game game) throws CommandExecuteException, InvalidPatternException;

	@Override
	public Command parse(String[] commandWords) throws CommandParserException, NoFileNameException, SpaceNameException {

		if (commandWords[0].equalsIgnoreCase(commandName) || commandWords[0].equalsIgnoreCase(commandFirstLetter)) {
			if (commandWords.length == 2) {
				this.filenameString = commandWords[1];
				return this;
			}

			else if (commandWords.length == 1)
				throw new NoFileNameException(commandName + " must be followed by a filename");

			else if (commandWords.length > 2)
				throw new SpaceNameException("Invalid filename: the filename contains spaces");

		}
		return null;
	}

}
