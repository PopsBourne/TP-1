package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;

public class ResetCommand extends NoParamsCommand {

	public ResetCommand() {
		super("[R]eset: ", "resets game.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.reset();
	}
	
	public Command parse(String[] commandInfoWords, Controller controller) {
		if (commandInfoWords.length == 1 && commandInfoWords[0].equalsIgnoreCase("reset")
				|| commandInfoWords[0].equalsIgnoreCase(commandName.substring(1, 2))) {
			return new ResetCommand();
		} else
			return null;
	}
}
