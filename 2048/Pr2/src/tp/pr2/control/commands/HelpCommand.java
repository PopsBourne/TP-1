package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

public class HelpCommand extends NoParamsCommand {

	/**Constructor.
	*/
	public HelpCommand(){
		super("help", "print this help message.");
	}
	
	public void execute(Game game, Controller controller) {		
		String s = CommandParser.commandHelp();
		game.showHelp(s);
	}
}
