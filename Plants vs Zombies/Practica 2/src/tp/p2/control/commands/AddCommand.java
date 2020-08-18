package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;
import tp.p2.logic.PlantFactory;
import tp.p2.logic.objects.plants.Plant;

public class AddCommand extends Command {

	private String plantName;
	private int x;
	private int y;

	public AddCommand() {
		super("[A]dd: ", "add flower.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		Plant plant = PlantFactory.getPlant(plantName);
		if (plant.getCost() <= game.getSunCoins()/* y si la pos del tablero esta ocupada */) { // Compruebo si tengo
																								// suncoins
			game.addPlantToGame(plant, x, y);
			game.setContCiclos(game.getContCiclos() + 1);// Ciclo++
			game.setSunCoins(game.getSunCoins() - plant.getCost());// Resto los suncoins gastados
			//new update
			// print (update?)
		} else
			System.out.println("You don't have enough suncoins!" + "\n");

	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		String firstLetter = commandName.substring(1, 2);
		//controller.setPrintGameState();

		if (commandWords[0].equalsIgnoreCase("add") || commandWords[0].equalsIgnoreCase(firstLetter)) {
			if (commandWords.length == 1) {
				System.out.println("Move must be a followed by a position");
				controller.setNoPrintGameState();
			} else if (commandWords.length > 4) {
				System.out.println("Unknown parameters");
				controller.setNoPrintGameState();
			} else if (commandWords.length == 4
					&& (Integer.parseInt(commandWords[2]) < 0 || Integer.parseInt(commandWords[2]) > 4
							|| commandWords[2].length() > 1)
					|| (Integer.parseInt(commandWords[2]) < 0 || Integer.parseInt(commandWords[2]) > 6
							|| commandWords[2].length() > 1)) {
				System.out.println("Invalid position");
				controller.setNoPrintGameState();
			}
			else{
				this.plantName = commandWords[1];
				this.x = Integer.parseInt(commandWords[2]);
				this.y = Integer.parseInt(commandWords[3]);
				return this;
			}
		}
		return null;
	}
}
