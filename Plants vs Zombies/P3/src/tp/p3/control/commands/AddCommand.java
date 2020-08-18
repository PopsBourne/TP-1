package tp.p3.control.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParserException;
import tp.p3.logic.Game;
import tp.p3.logic.PlantFactory;
import tp.p3.logic.objects.plants.Plant;

public class AddCommand extends Command {

	private String plantName;
	private int x;
	private int y;

	public AddCommand() {
		super("add", "a", "[A]dd: add flower.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		Plant plant = PlantFactory.getPlant(plantName);
		boolean result = false;
		if (plant == null)
			throw new CommandExecuteException(
					"Unknown plant name: " + plantName + ". Use 'list' to see the avalaible plants");
		else {
			if (!game.isPositionEmpty(x, y))
				throw new CommandExecuteException(
						"Failed to add " + plantName + ": (" + x + ", " + y + ") is already occupied");
			if (plant.getCost() > game.getSunCoins())
				throw new CommandExecuteException("Failed to add " + plantName + ": not enough suncoins to buy it");
			else {
				game.addPlantToGame(plant, x, y);
				game.setContCiclos(1);
				game.setSunCoins(-plant.getCost());
				result = true;
			}
		}
		return result;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParserException {

		if (commandWords[0].equalsIgnoreCase(commandName) || commandWords[0].equalsIgnoreCase(commandFirstLetter)) {

			if (commandWords.length == 1)
				throw new CommandParserException("Add must be a followed by a plant");
			else if (commandWords.length == 2)
				throw new CommandParserException("Type of plant must be a followed by a position");
			else if (commandWords.length > 4)
				throw new CommandParserException(
						"Incorrect number of arguments for add command: [A]dd <plant> <x> <y>");

			else if (commandWords.length == 4) {

				try {
					this.x = Integer.parseInt(commandWords[2]);
					this.y = Integer.parseInt(commandWords[3]);

					if ((x < 0 || x > 3 || commandWords[2].length() > 1)
							|| (y < 0 || y > 5 || commandWords[2].length() > 1)) {
						throw new CommandParserException(
								"Failed to add " + plantName + ": (" + x + ", " + y + ") is an invalid position");
					} else {
						this.plantName = commandWords[1];
						this.x = Integer.parseInt(commandWords[2]);
						this.y = Integer.parseInt(commandWords[3]);
						return this;
					}

				} catch (NumberFormatException e) {
					throw new CommandParserException(
							"Invalid argument for " + commandName + " command, number expected: [A]dd <plant> <x> <y>",
							e);
				}
			}
			throw new CommandParserException("Unknown command. Use 'help' to see the avalaible commands");
		}

		return null;
	}
}
