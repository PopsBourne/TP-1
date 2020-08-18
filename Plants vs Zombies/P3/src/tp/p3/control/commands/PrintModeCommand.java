package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParserException;
import tp.p3.logic.Game;
import tp.p3.logic.board.DebugPrinter;
import tp.p3.logic.board.ReleasePrinter;

public class PrintModeCommand extends Command {

	private String mode;

	public PrintModeCommand() {
		super("print", "p", "[P]rintMode: change print mode [Release|Debug].");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean result = false;
		int tam = game.getTamListPln() + game.getTamListZmb();
		if (this.mode.equalsIgnoreCase("debug") || this.mode.equalsIgnoreCase("d")) {
			new DebugPrinter(game, tam);
			result = false;
		} else if (this.mode.equalsIgnoreCase("release") || this.mode.equalsIgnoreCase("r")) {
			new ReleasePrinter(game, 4, 8);
			result = true;
		} else
			throw new CommandExecuteException("Unknown print mode: " + mode);
		return result;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParserException {

		if (commandWords[0].equalsIgnoreCase(commandName) || commandWords[0].equalsIgnoreCase(commandFirstLetter)) {

			if (commandWords.length == 1 || commandWords.length > 2) {
				throw new CommandParserException(
						"Incorrect number of arguments for " + commandName + " command: [P]rintMode release|debug");
			} else if (commandWords.length == 2) {
				this.mode = commandWords[1];
				return this;
			} else
				throw new CommandParserException("Unknown command. Use 'help' to see the avalaible commands");
		}

		return null;
	}

}
