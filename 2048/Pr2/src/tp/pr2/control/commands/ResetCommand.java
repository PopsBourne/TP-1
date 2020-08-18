package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

public class ResetCommand extends NoParamsCommand {

	/**Constructor.
	*/
	public ResetCommand(){
		super("reset", "start a new game.");	
	}
	
	public void execute(Game game, Controller controller) {
		game.reset();
	}
	
	public String helpText() {
		return super.helpText();
	}
}