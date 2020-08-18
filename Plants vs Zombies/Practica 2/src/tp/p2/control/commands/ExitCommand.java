package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;
import tp.p2.util.MyStringUtils;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("[E]xit: ", "terminate the program.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.exit();
	}

	public Command parse(String[] commandInfoWords, Controller controller) {
		if (commandInfoWords.length == 1 && (commandInfoWords[0].equalsIgnoreCase("exit")
				|| commandInfoWords[0].equalsIgnoreCase(commandName.substring(1, 2)))) {
			return new ExitCommand();
		} else
			return null;
	}
}