package tp.p3.control.commands;

import tp.p3.logic.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("exit", "e", "[E]xit: terminate the program.");
	}

	@Override
	public boolean execute(Game game) {
		return game.exit();
	}

	public Command parse(String[] commandInfoWords) {
		if (commandInfoWords.length == 1 && (commandInfoWords[0].equalsIgnoreCase(commandName)
				|| commandInfoWords[0].equalsIgnoreCase(commandFirstLetter))) {
			return this;
		} else
			return null;
	}
}