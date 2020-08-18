package tp.p2.control.commands;

import tp.p2.control.Controller;
import tp.p2.logic.Game;
import tp.p2.logic.ZombieFactory;
import tp.p2.logic.objects.zombies.Zombie;

public class NoneCommand extends NoParamsCommand {

	public NoneCommand() {
		super("[U]pdate: ", "update game.");
		
	}

	@Override
	public void execute(Game game, Controller controller) {
		Zombie zombie = ZombieFactory.getRandZombie(game.get_rand());
		game.addZombieToGame(zombie);
		game.update();
	}

	public Command parse(String[] commandInfoWords, Controller controller) {
		if (commandInfoWords.length == 1 && commandInfoWords[0].equalsIgnoreCase("update")
				|| commandInfoWords[0].equalsIgnoreCase(commandName.substring(1, 2))) {
			return new NoneCommand();
		} else
			return null;
	}

}
