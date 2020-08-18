package tp.p3.control.commands;


import tp.p3.logic.Game;
import tp.p3.logic.PlantFactory;


public class ListCommand extends NoParamsCommand {

	public ListCommand() {
		super("list", "l", "[L]ist: print the list of available plants.");
	}

	@Override
	public boolean execute(Game game) {
		String l = PlantFactory.listOfAvilablePlants();
		if (l == null)
			return false;
		else
			System.out.println(l);
		return true;
	}

	public Command parse(String[] commandInfoWords) {//utilizar parse noParamscommand
		if (commandInfoWords.length == 1 && commandInfoWords[0].equalsIgnoreCase(commandName)
				|| commandInfoWords[0].equalsIgnoreCase(commandFirstLetter)) {
			return this;
		} else
			return null;
	}
}