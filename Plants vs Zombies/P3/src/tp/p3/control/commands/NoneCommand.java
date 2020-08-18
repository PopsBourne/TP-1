package tp.p3.control.commands;


import tp.p3.exceptions.CommandParserException;
import tp.p3.logic.Game;
import tp.p3.logic.ZombieFactory;
import tp.p3.logic.objects.zombies.Zombie;

public class NoneCommand extends NoParamsCommand {

	public NoneCommand() {
		super("update","u", "[U]pdate: update game.");

	}

	@Override
	public boolean execute(Game game){
		Zombie zombie = ZombieFactory.getZombie();
		if (zombie == null)
			return false;
		else {
			game.addZombieToGame(zombie);
			game.update();
			return true;
		}
	}

	public Command parse(String[] commandInfoWords) throws CommandParserException {
		if (commandInfoWords[0].equalsIgnoreCase(commandName)
				|| commandInfoWords[0].equalsIgnoreCase(commandFirstLetter)) {
			if(commandInfoWords.length == 1)
			return this;
			else throw new CommandParserException("None command has no arguments");
				
		} else
			return null;
	}

}
