package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class HelpCommand extends NoParamsCommand {

	/**Constructor.
	*/
	public HelpCommand(){
		super("help", "print this help message.");
	}
	
	public boolean execute(Game game) {		
		String s = CommandParser.commandHelp();
		return game.showHelp(s);
	}
}