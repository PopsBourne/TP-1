package tp.pr3.control.commands;
import tp.pr3.logic.multigames.Game;

public class UndoCommand extends NoParamsCommand {
	
	/**Constructor.
	*/
	public UndoCommand(){
		super("undo", "undo the last command.");
	}
	
	public boolean execute(Game game) {		
		return game.undo();
	}
}