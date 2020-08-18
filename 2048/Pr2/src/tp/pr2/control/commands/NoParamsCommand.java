package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

public abstract class NoParamsCommand extends Command {
	
	/**Constructor.
	  @param commandInfo
	  @param helpInfo
	*/
	public NoParamsCommand(String commandInfo, String helpInfo){
		super(commandInfo,helpInfo);
	}
	
	public abstract void execute(Game game, Controller controller);
	
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords[0].equalsIgnoreCase(commandName))
			return this; // help reset exit llamaran a la funcion parser (no NoParamsCommand) por eso el this
		return null;
	}
}