package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.exceptions.NoGameException;
import tp.pr3.exceptions.UnknownGameException;
import tp.pr3.logic.multigames.Game;

public class PlayCommand extends Command {
	protected int boardSize, initialCells;
	protected long randomSeed;
	protected GameType gameType;

	/**Constructor.
	*/
	public PlayCommand() {
		super("play <game>", "start a new game of one of the game types: original, fib, inverse.");
	}

	public boolean execute(Game game) {
		boolean badInput = true;
		String[] commandL;
		Scanner in = new Scanner(System.in);
		while (badInput) {
			// boardSize
			try {
				System.out.println("Please enter de size of the board:");
				commandL = in.nextLine().split("\\s+");
				if (commandL.length == 1 && commandL[0].isEmpty()) {
					System.out.println("Using the default size of the board: 4");
					boardSize = 4;
					badInput = false;
				} else if (commandL.length > 1) {
						System.out.println("Please provide a single positive integer or press return");
						badInput = true;
					} else if (commandL.length == 1) {
								boardSize = Integer.parseInt(commandL[0]);
								if (boardSize < 0) {
									System.out.println("The size of the board must be positive");
									badInput = true;
								} else badInput = false;
							}
			}	
			catch (NumberFormatException n) {
				System.out.println("The size of the board must be a number");
				badInput = true;
			}
		}
		
		badInput = true;
		while (badInput) {
			//initcells
			try {
				System.out.println("Please enter the number of initials cells:");
				commandL = in.nextLine().split("\\s+");
				if (commandL.length == 1 && commandL[0].isEmpty()) {
					System.out.println("Using the default number of initial cells: 2");
					initialCells = 2;
					badInput = false;
				} else if (commandL.length > 1) {
					System.out.println("Please provide a single positive integer or press return");
					badInput = true;
				}else if (commandL.length == 1) {
					initialCells = Integer.parseInt(commandL[0]);
						if (initialCells > boardSize * boardSize || initialCells < 0) {
							System.out.println("The number of initial cells must be less than the number of cells on the board");
							badInput = true;
						} 	else badInput = false;
					}
			}
			catch(NumberFormatException n) {
				System.out.println("The number of initial cells must be a number");
				badInput = true;
			}
		}
		
		badInput = true;
		while(badInput) {
			// random seed
			try {
				System.out.println("Please enter the seed for the pseudo-random number generator:");
				commandL = in.nextLine().split("\\s+");
				if (commandL.length == 1 && commandL[0].isEmpty()) {
					randomSeed = game.getCurrentSeed();
					System.out.println("Using the default seed for the pseudo-random number generator: " + randomSeed);
					badInput = false;
				} else if (commandL.length > 1) {
					System.out.println("Please provide a single positive integer or press return");
					badInput = true;
				}else {
					randomSeed = Integer.parseInt(commandL[0]);	
					badInput = false;
				}
			}
			catch(NumberFormatException n) {
				System.out.println("The seed for the pseudo-random number generator must be a number");
				badInput = true;
			}
		}
		if(!badInput) {
			game.setCurrentSeed(randomSeed);
			game.setGameType(gameType);
			game.set_size(boardSize);
			game.set_initCells(initialCells);
			game.reset();
			return true;
		}
		return false;
	}

	public Command parse(String[] commandWords, Scanner in) throws NoGameException, UnknownGameException {
		if (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName)) {
			//controller.setNoPrintGameState();
			throw new NoGameException("Play must be followed by a game: original, fib, inverse");
		} else if (commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName)) {
			if (commandWords[1].equalsIgnoreCase("fib"))
				gameType = GameType.FIB;
			else if (commandWords[1].equalsIgnoreCase("inverse"))
				gameType = GameType.INV;
			else if (commandWords[1].equalsIgnoreCase("original"))
				gameType = GameType.ORIG;
			else {
				//controller.setNoPrintGameState();
				throw new UnknownGameException("Unknown game type for play command");
			}
			return this;
		} else if (commandWords.length > 1 && commandWords[0].equalsIgnoreCase(commandName)){
			//controller.setNoPrintGameState();
			throw new UnknownGameException("Unknown game type for play command");
		}
		return null;
	}
}