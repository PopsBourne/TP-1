package tp.p3.control;

import java.util.Scanner;

import tp.p3.control.commands.Command;
import tp.p3.control.commands.CommandParser;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParserException;
import tp.p3.exceptions.InvalidPatternException;
import tp.p3.exceptions.NoFileNameException;
import tp.p3.exceptions.SpaceNameException;
import tp.p3.logic.Game;
import tp.p3.util.MyStringUtils;

public class Controller {

	private Game game;
	private Scanner in;
	private boolean printState;

	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
		this.printState = false;
	}

	public void run() {
		printGame();
		while (!game.isFin()) {
			if (printState == true) {
				printGame();
			}
			System.out.println("\n");
			System.out.println("Command > ");
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			Command command = null;

			try {
				command = CommandParser.parseCommand(words);
				if (command != null) {
						if (command.execute(game))
							printGame();
				} else
					System.err.println("Unknown command. Use 'help' to see the avalaible commands");

			} catch (CommandParserException | CommandExecuteException | InvalidPatternException | NoFileNameException | SpaceNameException e) {
				System.out.format(e.getMessage() + " %n %n");
			}
		}

		if (game.isFin()) {
			printGame();
			if (!game.isLoseGame())
				System.out.println("Player wins!");
			else {
				System.out.println("Zombies win...\n");
				System.out.println(MyStringUtils.centre("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>", 100));
				System.exit(0);
			}
		}

	}

	private void printGame() {
		game.setGamePrinter();
	}

	public void setNoPrintGameState() {
		printState = false;
	}

	public void setPrintGameState() {
		printState = true;
	}

}
