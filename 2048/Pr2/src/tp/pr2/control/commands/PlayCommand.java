package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.multigames.GameRules;
import tp.pr2.logic.multigames.Rules2048;
import tp.pr2.logic.multigames.RulesFib;
import tp.pr2.logic.multigames.RulesInverse;

public class PlayCommand extends Command {
	protected int boardSize, initialCells;
	protected long randomSeed;
	protected GameType gameType;

	/**Constructor.
	*/
	public PlayCommand() {
		super("play <game>", "start a new game of one of the game types: original, fib, inverse.");
	}

	public void execute(Game game, Controller controller) {
		boolean badInput = true;
		String[] commandL;
	
		while (badInput) {
			// boardSize
			System.out.println("Please enter de size of the board:");
			commandL = controller.getIn().nextLine().split("\\s+");
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
		
		badInput = true;
		while (badInput) {
			//initcells
				System.out.println("Please enter the number of initials cells:");
				commandL = controller.getIn().nextLine().split("\\s+");
				if (commandL.length == 1 && commandL[0].isEmpty()) {
					System.out.println("Using the default number of initial cells: 2");
					initialCells = 2;
					badInput = false;
				}else {
					initialCells = Integer.parseInt(commandL[0]);
					if (initialCells > boardSize * boardSize || initialCells < 0) {
						System.out.println("The number of initial cells must be less than the number of cells on the board");
						badInput = true;
					} else badInput = false;
				}
		}
		
		badInput = true;
		while (badInput) {
			// random seed
			System.out.println("Please enter the seed for the pseudo-random number generator:");
			commandL = controller.getIn().nextLine().split("\\s+");
			if (commandL.length == 1 && commandL[0].isEmpty()) {
				System.out.println("Using the default seed for the pseudo-random number generator");
				randomSeed = game.getCurrentSeed();
				badInput = false;
			} else {
				randomSeed = Integer.parseInt(commandL[0]);	
				badInput = false;
			}
		}
		//crea un nuevo juego fib, orig, inv
		if(!badInput) {
			GameRules gr = null;
			switch(gameType){
			case FIB:
				gr = new RulesFib(); break;
			case INV:
				gr = new RulesInverse(); break;
			case ORIG:
				gr = new Rules2048(); break;
			}	
			//game = new Game(gr, randomSeed, boardSize, initialCells);
			game.setCurrentSeed(randomSeed);
			game.setCurrentRules(gr);
			game.set_size(boardSize);
			game.set_initCells(initialCells);
			game.reset();
		}
	}

	public Command parse(String[] commandWords, Controller controller) {
		if (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName)) {
			System.out.println("Play must be followed by a game: original, fib, inverse");
			controller.setNoPrintGameState();
			return null;
		} else if (commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName)) {
			if (commandWords[1].equalsIgnoreCase("fib"))
				gameType = GameType.FIB;
			else if (commandWords[1].equalsIgnoreCase("inv"))
				gameType = GameType.INV;
			else if (commandWords[1].equalsIgnoreCase("orig"))
				gameType = GameType.ORIG;
			else {
				System.out.println("Unknown game type for game command");
				controller.setNoPrintGameState();
				return null;
			}
			return this;
		} else {
			System.out.println("Unknown game type for game command");
			controller.setNoPrintGameState();
			return null;
		}
	}
}