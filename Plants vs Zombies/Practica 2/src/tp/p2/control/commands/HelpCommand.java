package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("[H]elp: ", "print this help message.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		String s = CommandParser.commandHelp();
		System.out.println(s);
	}
	
	public Command parse(String[] commandInfoWords, Controller controller) {
		if (commandInfoWords.length == 1 && commandInfoWords[0].equalsIgnoreCase("help")
				|| commandInfoWords[0].equalsIgnoreCase(commandName.substring(1, 2))) {
			return new HelpCommand();
		} else
			return null;
	}
}