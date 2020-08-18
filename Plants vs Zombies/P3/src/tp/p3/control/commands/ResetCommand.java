package tp.p3.control.commands;

import tp.p3.logic.Game;

public class ResetCommand extends NoParamsCommand {

	public ResetCommand() {
		super("reset", "r", "[R]eset: resets game.");
	}

	@Override
	public boolean execute(Game game) {
		return game.reset();
	}

	public Command parse(String[] commandInfoWords) {
		if (commandInfoWords.length == 1 && commandInfoWords[0].equalsIgnoreCase(commandName)
				|| commandInfoWords[0].equalsIgnoreCase(commandFirstLetter)) {
			return this;
		} else
			return null;
	}
}
