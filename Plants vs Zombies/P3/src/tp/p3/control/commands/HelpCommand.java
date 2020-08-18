package tp.p3.control.commands;


import tp.p3.exceptions.CommandParserException;
import tp.p3.logic.Game;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("help", "h", "[H]elp: print this help message.");
	}

	@Override
	public boolean execute(Game game) {
		String s = CommandParser.commandHelp();
		System.out.println(s);
		return false;
	}

	public Command parse(String[] commandInfoWords) throws CommandParserException {
		if (commandInfoWords[0].equalsIgnoreCase(commandName)
				|| commandInfoWords[0].equalsIgnoreCase(commandFirstLetter)) {
			if (commandInfoWords.length == 1)
				return this;
			else
				throw new CommandParserException(commandName +" command has no arguments");
		} else
			return null;
	}
}