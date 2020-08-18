package tp.pr3.control.commands;

import java.util.Scanner;

public class CommandParser {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(),
			new ExitCommand(), new UndoCommand(), new RedoCommand(), new MoveCommand(), new PlayCommand(),
			new SaveCommand(""), new LoadCommand("") } ;
	
	public static Command parseCommand(String[] commandWords){
		Command command = null;
		Scanner in = new Scanner(System.in);
		boolean show = true;
		for (Command i : availableCommands) {
			try {
				command = i.parse(commandWords,in);
			}catch (Exception e) {
				System.out.println(e.getMessage());
				show = false;
			}
			if (command != null) return command;
		}
		if (command == null && show) System.out.println("Unknown command. Use 'help' to see the avalaible commands");
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