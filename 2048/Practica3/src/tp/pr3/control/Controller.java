package tp.pr3.control;

//import java.util.Random;
import java.util.Scanner;
import tp.pr3.control.commands.Command;
import tp.pr3.control.commands.CommandParser;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.logic.multigames.Game;

public class Controller {
	private Game game;
	private Scanner in;
	// public static final String commandErrorMsg = "Unknown command. Use 'help' to
	// see the available commands";
	private static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * Constructor.
	 * 
	 * @param game
	 * @param in
	 */
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}

	public void run() {
		
		while (!game.isFinalizado()) { // !game.win() && !game.lose()
			System.out.println("Command > ");
			String m;
			m = in.nextLine();
			m = m.trim();
			String[] words = m.split(" +");
			Command command = CommandParser.parseCommand(words); // parseCommand es un metodo estatico
			if (command != null) {
				// if(command.getCommandName() == "exit")
				// System.out.println("Game over...");
				try {
					command.execute(game);
				} catch (GameOverException e) {
					String n = NEWLINE;
					System.out.println(n);
					System.out.println(e.getMessage());
					
				}
			}
		}
	}

	public Scanner getIn() {
		return in;
	}

	public void setIn(Scanner in) {
		this.in = in;
	}
	/*
	 * public static void main(String args[]) { Random rand = new Random(); Game
	 * partida11 = new Game(2, 2, rand); Scanner in11 = new Scanner(System.in);
	 * Controller c = new Controller(partida11, in11);
	 * //System.out.println(partida11.toString()); c.run(); }
	 */
}