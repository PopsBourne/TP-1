package tp.pr1;

import java.util.Random;
import java.util.Scanner;

public class Controller {
	private static final String NEWLINE = System.getProperty("line.separator");
	private Game game;
	private Scanner in;
	
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
		}

	//realiza la simulacion del juego
	public void run() {
		while(game.getHighest() < 2048 && !game.isFinalizado()) {
			System.out.println("Command > ");
			String m;
			m = in.nextLine();
			m = m.trim();
			String[] words = m.split(" +");
			
			if (words.length == 1 && !words[0].equalsIgnoreCase("exit") && !words[0].equalsIgnoreCase("help") && !words[0].equalsIgnoreCase("reset")){
				if(words[0].equalsIgnoreCase("move"))
					System.out.println("Move must be a followed by a direction: up, down, left or right");
				else
					System.out.println("Unknown command");
			}	
			else if  ( words.length == 3 || words.length > 2 || words.length == 0)
				System.out.println("Unknown command");
			else if (!words[0].equalsIgnoreCase("move") && !words[0].equalsIgnoreCase("exit") && !words[0].equalsIgnoreCase("help") && !words[0].equalsIgnoreCase("reset"))
				System.out.println("Unknown command");
			else if(words.length == 2 && words[0].equalsIgnoreCase("move") && ( words[1].equalsIgnoreCase("up") ||words[1].equalsIgnoreCase("down") || words[1].equalsIgnoreCase("right") ||
			   words[1].equalsIgnoreCase("left"))){
				words[1] = words[1].toLowerCase();
				switch(words[1]){
				case "up":
					game.move(Direction.UP); break;
				case "down":
					game.move(Direction.DOWN); break;
				case "right":
					game.move(Direction.RIGHT); break;
				case "left":
					game.move(Direction.LEFT); break;
				}
						
				if(!game.finalizado() && !game.lleno()){
					game.generaCelda(false);
				System.out.println(game.toString());
				}
				else if(game.finalizado()){
						String a = NEWLINE;
						System.out.println(a);
						System.out.println("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>");
					} else System.out.println(game.toString());
				
			}
			else if (m.equalsIgnoreCase("reset")){
				game.reset();
				System.out.println(game.toString());
				}
			else if (m.equalsIgnoreCase("help")){
					System.out.println("Move <direction>: execute a move in one of the four directions, up, down, left, right ");
					System.out.println("Reset: start a new game ");
					System.out.println("Help: print this help message ");
					System.out.println("Exit: terminate the program ");
					}
			else if(m.equalsIgnoreCase("exit")){
							//System.out.println("Game over");
							game.setFinalizado(true);
						}
			else System.out.println("Unknown direction for move command");
			if(game.finalizado()){
			String a = NEWLINE;
			System.out.println(a);
			System.out.println("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>");
			}
		}
		if (game.getHighest() == 2048)
			System.out.println("Well Done !");
	}
	
	/*public static void main(String args[]) {
		Random rand = new Random();
		Game partida11 = new Game(2, 2, rand);
		Scanner in11 = new Scanner(System.in);
		Controller c = new Controller(partida11, in11);
		//System.out.println(partida11.toString());
		c.run();
	}*/
}