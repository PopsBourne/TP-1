package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.Direction;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.NoArgumentException;
import tp.pr3.exceptions.UnknownDirectionException;
import tp.pr3.logic.multigames.Game;

public class MoveCommand extends Command{
	Direction dir;
	
	/**Constructor.
	*/
	public MoveCommand(){
		super("move <direction>", "execute a move in one of the directions: up, down, left, right.");
	}
	
	public boolean execute(Game game) throws GameOverException{
		switch(dir){
		case UP:
			game.move(Direction.UP); break;
		case DOWN:
			game.move(Direction.DOWN); break;
		case RIGHT:
			game.move(Direction.RIGHT); break;
		case LEFT:
			game.move(Direction.LEFT); break;
		default: return false;
		}	
		return true;
	}

	public Command parse(String[] commandWords, Scanner in) throws NoArgumentException, UnknownDirectionException{
		//controller.setPrintGameState();
		if(commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName)) {
			//System.out.println("Move must be a followed by a direction: up, down, left or right");
			//controller.setNoPrintGameState();
			throw new NoArgumentException("Move must be a followed by a direction: up, down, left or right");
			
		}
		if(commandWords.length > 2 && commandWords[0].equalsIgnoreCase(commandName) || 
		   commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName) &&
		  !commandWords[1].equalsIgnoreCase("up") && !commandWords[1].equalsIgnoreCase("down") &&
		  !commandWords[1].equalsIgnoreCase("right") && !commandWords[1].equalsIgnoreCase("left")) {
			 //controller.setNoPrintGameState();
			 throw new UnknownDirectionException("Move must be a followed by a direction: up, down, left or right");
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