package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.exceptions.GameOverException;
import tp.pr3.logic.multigames.Game;

public abstract class NoParamsCommand extends Command {
	
	/**Constructor.
	  @param commandInfo
	  @param helpInfo
	*/
	public NoParamsCommand(String commandInfo, String helpInfo){
		super(commandInfo,helpInfo);
	}
	
	public abstract boolean execute(Game game);
	
	public Command parse(String[] commandWords, Scanner in) {
		if(commandWords[0].equalsIgnoreCase(commandName))
			return this; // help reset exit llamaran a la funcion parser (no NoParamsCommand) por eso el this
		return null;
	}
}