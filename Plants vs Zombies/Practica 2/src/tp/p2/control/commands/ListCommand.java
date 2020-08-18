package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;
import tp.p2.logic.PlantFactory;
import tp.p2.logic.objects.plants.Plant;

public class ListCommand extends NoParamsCommand {

	public ListCommand() {
		super("[L]ist: ", "print the list of available plants.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		String l = PlantFactory.listOfAvilablePlants();
		System.out.println(l);
	}
	
	public Command parse(String[] commandInfoWords, Controller controller) {
		if (commandInfoWords.length == 1 && commandInfoWords[0].equalsIgnoreCase("list") 
				|| commandInfoWords[0].equalsIgnoreCase(commandName.substring(1, 2))) {
			return new ListCommand();
		} else
			return null;
	}
}