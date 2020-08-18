package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.InvalidPatternException;
import tp.pr3.exceptions.NoArgumentException;
import tp.pr3.exceptions.NoFileNameException;
import tp.pr3.exceptions.NoGameException;
import tp.pr3.exceptions.SpaceNameException;
import tp.pr3.exceptions.UnknownDirectionException;
import tp.pr3.exceptions.UnknownGameException;
import tp.pr3.logic.multigames.Game;

public abstract class Command {
	private String helpText;
	private String commandText;
	protected final String commandName;

	/**Constructor.
	 @param commandInfo
	 @param helpInfo
	*/
	public Command(String commandInfo, String helpInfo) {
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	public abstract boolean execute(Game game) throws GameOverException;

	public abstract Command parse(String[] commandWords, Scanner in) throws NoArgumentException, UnknownDirectionException, NoGameException, UnknownGameException, InvalidPatternException, NoFileNameException, SpaceNameException, Exception;

	public String helpText() {
		return " " + commandText + ": " + helpText;
	}
	public String getCommandName() {
		return commandName;
	}
}