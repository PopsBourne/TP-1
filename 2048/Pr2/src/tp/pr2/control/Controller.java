package tp.pr2.control;

//import java.util.Random;
import java.util.Scanner;

import tp.pr2.control.commands.Command;
import tp.pr2.control.commands.CommandParser;
import tp.pr2.logic.multigames.Game;

public class Controller {
	private Game game;
	private Scanner in;
	private boolean printGameState;
	
	/**Constructor.
	 @param game
	 @param in
	*/
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
		printGameState = true;
	}

	public void run() {
		while(!game.isFinalizado()) { // !game.win() && !game.lose()
			System.out.println("Command > ");
			String m;
			m = in.nextLine();
			m = m.trim();
			String[] words = m.split(" +");
			Command command = CommandParser.parseCommand(words, this); //parseCommand es un metodo estatico
			if(command != null) {
				if(command.getCommandName() == "exit")
					System.out.println("Game over...");
				command.execute(game, this);
			}else {
				if (printGameState)
				System.out.println("Unknown command. Use 'help' to see the avalaible commands");
			}
		}
	}
	
	public void setNoPrintGameState() {
		printGameState = false;
	}
	public void setPrintGameState() {
		printGameState = true;
	}
	public Scanner getIn() {
		return in;
	}
	public void setIn(Scanner in) {
		this.in = in;
	}
	/*
	public static void main(String args[]) {
		Random rand = new Random();
		Game partida11 = new Game(2, 2, rand);
		Scanner in11 = new Scanner(System.in);
		Controller c = new Controller(partida11, in11);
		//System.out.println(partida11.toString());
		c.run();
	}*/
}