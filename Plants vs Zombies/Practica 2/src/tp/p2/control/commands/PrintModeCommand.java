package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;
import tp.p2.logic.board.DebugPrinter;
import tp.p2.logic.board.ReleasePrinter;

public class PrintModeCommand extends Command {

	private String mode;
	private DebugPrinter dbg;
	private ReleasePrinter rls;

	public PrintModeCommand() {
		super("[P]rintMode: ", "change print mode [Release|Debug].");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// BoardPrinter gmp = new BoardPrinter(game, 4, 8);
		int tam = game.getTamListPln() + game.getTamListZmb();
		if (this.mode.equalsIgnoreCase("debug") || this.mode.equalsIgnoreCase("d")) {
			dbg = new DebugPrinter(game, tam);
		//	dbg.printGame(game);
		} else {
			rls = new ReleasePrinter(game, 4, 8);
		//	rls.printGame(game);
		}
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		//mode = commandWords[1];
		//String firstLetter = commandName.substring(1, 2);

		if (commandWords[0].equalsIgnoreCase("print") || commandWords[0].equalsIgnoreCase(commandName.substring(1, 2))) {
			if (commandWords.length == 1) {
				System.out.println("Move must be a followed by a mode");
				controller.setNoPrintGameState();
			} else if (commandWords.length > 2) {
				System.out.println("Unknown mode");
				controller.setNoPrintGameState();
			} else {
				this.mode = commandWords[1];
				return this;
			}
		}
		return null;
	}

}
