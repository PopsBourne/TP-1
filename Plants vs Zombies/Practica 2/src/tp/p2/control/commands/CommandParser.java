package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.control.commands.Command;

public class CommandParser {

	private static Command[] availableCommands = { new AddCommand(), new HelpCommand(), new ResetCommand(),
			new ExitCommand(), new ListCommand(), new NoneCommand(),new PrintModeCommand() };
	
	public static Command parseCommand(String[] commandWords, Controller controller) {
		Command command;
		for (Command i : availableCommands) {
			command = i.parse(commandWords, controller);
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
