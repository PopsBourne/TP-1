package tp.p2.control;

import java.util.Scanner;

import tp.p2.control.commands.Command;
import tp.p2.control.commands.CommandParser;
import tp.p2.logic.Game;
import tp.p2.logic.board.ReleasePrinter;
import tp.p2.util.MyStringUtils;

public class Controller {

	private Game game;
	private Scanner in;
	private ReleasePrinter board;
	private boolean printState;

	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
		this.board = new ReleasePrinter(game, 4, 8);
		this.printState = false;
	}

	public void run() {
		// Pintamos al iniciar
		// printGame();
		while (!game.isFin()) {
			if (printState == true) {
				printGame();
			}
			System.out.println("Command > ");
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			Command command = CommandParser.parseCommand(words, this);
			if (command != null) {
				command.execute(game, this);
				setPrintGameState();
			} else {
				System.err.println("Unknown Command");
				setNoPrintGameState();
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
	//	System.out.println(game.draw());
		this.board.encodeGame(game);
	}

	public void setNoPrintGameState() {
		printState = false;
	}

	public void setPrintGameState() {
		printState = true;
	}

}
