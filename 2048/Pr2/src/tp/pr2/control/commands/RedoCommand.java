package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

public class RedoCommand extends NoParamsCommand {

	/**Constructor.
	*/
	public RedoCommand(){
		super("redo", "redo the last done command.");
	}

	public void execute(Game game, Controller controller) {		
		game.redo();
	}
}