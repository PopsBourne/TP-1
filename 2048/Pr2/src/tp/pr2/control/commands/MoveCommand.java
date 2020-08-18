package tp.pr2.control.commands;

import tp.pr2.Direction;
import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

public class MoveCommand extends Command{
	Direction dir;
	
	/**Constructor.
	*/
	public MoveCommand(){
		super("move <direction>", "execute a move in one of the directions: up, down, left, right.");
	}
	
	public void execute(Game game, Controller controller){
		switch(dir){
		case UP:
			game.move(Direction.UP); break;
		case DOWN:
			game.move(Direction.DOWN); break;
		case RIGHT:
			game.move(Direction.RIGHT); break;
		case LEFT:
			game.move(Direction.LEFT); break;
		}	
	}

	public Command parse(String[] commandWords, Controller controller){
		controller.setPrintGameState();
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName)) {
			System.out.println("Move must be a followed by a direction: up, down, left or right");
			controller.setNoPrintGameState();
		}
		if(commandWords.length > 2 && commandWords[0].equalsIgnoreCase(commandName) || 
		   commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName) &&
		  !commandWords[1].equalsIgnoreCase("up") && !commandWords[1].equalsIgnoreCase("down") &&
		  !commandWords[1].equalsIgnoreCase("right") && !commandWords[1].equalsIgnoreCase("left")) {
			 System.out.println("Unknown direction for move command");
			 controller.setNoPrintGameState();
		}	
		if(commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName) &&
	 	   (commandWords[1].equalsIgnoreCase("up") || commandWords[1].equalsIgnoreCase("down") ||
	 	    commandWords[1].equalsIgnoreCase("right") || commandWords[1].equalsIgnoreCase("left"))) {
		    commandWords[1] = commandWords[1].toLowerCase();
				switch(commandWords[1]){
				case "up":
					dir = Direction.UP; break;
				case "down":
					dir = Direction.DOWN;break;
				case "right":
					dir = Direction.RIGHT;break;
				case "left":
					dir = Direction.LEFT;break;
				}
				return this;
		}
		return null;
	}
}