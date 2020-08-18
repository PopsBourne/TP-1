package tp.pr3.control.commands;
import tp.pr3.logic.multigames.Game;

public class RedoCommand extends NoParamsCommand {

	/**Constructor.
	*/
	public RedoCommand(){
		super("redo", "redo the last done command.");
	}

	public boolean execute(Game game) {		
		return game.redo();
	}
}