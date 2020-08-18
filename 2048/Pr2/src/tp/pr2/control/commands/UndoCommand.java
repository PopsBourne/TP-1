package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

public class UndoCommand extends NoParamsCommand {
	
	/**Constructor.
	*/
	public UndoCommand(){
		super("undo", "undo the last command.");
	}
	
	public void execute(Game game, Controller controller) {		
		game.undo();
	}
}