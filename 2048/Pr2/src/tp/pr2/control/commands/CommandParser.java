package tp.pr2.control.commands;

import tp.pr2.control.Controller;

public class CommandParser {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(),
			new ExitCommand(), new UndoCommand(), new RedoCommand(), new MoveCommand(), new PlayCommand() } ;
	
	public static Command parseCommand(String[] commandWords, Controller controller){
		Command command;
		controller.setPrintGameState();
		for (Command i : availableCommands) {
			command = i.parse(commandWords, controller);
			if (command != null) return command;
		}
		return null;
	}
	
	public static String commandHelp(){
		String n = NEWLINE;
		String help =  "The available commands are:" + n;
		for (Command i : availableCommands) {
			help += i.helpText();
			help += n;
		}
		return help;
	}
}