package tp.p3.control.commands;


import tp.p3.control.commands.Command;
import tp.p3.exceptions.CommandParserException;
import tp.p3.exceptions.NoFileNameException;
import tp.p3.exceptions.SpaceNameException;

public class CommandParser {

	private static Command[] availableCommands = { new AddCommand(),
			new HelpCommand(), new ResetCommand(), new ExitCommand(),
			new ListCommand(), new NoneCommand(), new PrintModeCommand(), new SaveCommand(), new LoadCommand() };

	public static Command parseCommand(String[] commandWords) throws CommandParserException, NoFileNameException, SpaceNameException {
		Command command = null;
		for (Command i : availableCommands) {
				command = i.parse(commandWords);
			if (command != null)
				return command;
		}
		return null;
	}

	public static String commandHelp() {
		String help = "The available commands are:" + "\n";
		for (Command i : availableCommands) {
			help += i.helpText();
			help += "\n";
		}
		return help;
	}
}
