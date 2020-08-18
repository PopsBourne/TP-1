package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class ResetCommand extends NoParamsCommand {

	/**Constructor.
	*/
	public ResetCommand(){
		super("reset", "start a new game.");	
	}
	
	public boolean execute(Game game) {
		return game.reset();
	}
	
	public String helpText() {
		return super.helpText();
	}
}