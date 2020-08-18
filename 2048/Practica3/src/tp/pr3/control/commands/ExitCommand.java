package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class ExitCommand extends NoParamsCommand {
	
	/**Constructor.
	*/
	public ExitCommand(){
		super("exit","terminate the program.");
	}

	public boolean execute(Game game) {
		System.out.println("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>");
		return game.setFinalizado(true);
	}
}