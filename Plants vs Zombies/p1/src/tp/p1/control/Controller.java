package tp.p1.control;

import java.util.Scanner;
import tp.p1.logic.Game;
import tp.p1.logic.objects.Peashooter;
import tp.p1.logic.objects.Sunflower;
import tp.p1.logic.objects.Zombie;
import tp.p1.util.MyStringUtils;

public class Controller {

	private Game game;
	private Scanner in;

	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}

	public void run() {
		while (!game.isFin()) {

			System.out.println("Command > ");
			String m = in.nextLine().trim();
			String[] words = m.split(" +");
			System.out.println("\n");

			// HELP //
			if (words[0].equalsIgnoreCase("help") || words[0].equalsIgnoreCase("h")) {
				System.out.println("Add <plant> <x> <y>: Adds a plant in position x,y.\n"
						+ "List: Prints the list of available plants.\n" + "Reset: start a new game.\n"
						+ "Help: print this help message.\n" + "Exit: terminates the program.\n"
						+ "[none]: Skips cycle.\n");
			}

			// RESET //
			else if (words[0].equalsIgnoreCase("reset") || words[0].equalsIgnoreCase("r"))
				game.reset();

			// EXIT //
			else if (m.equalsIgnoreCase("exit") || m.equalsIgnoreCase("e")) {
				System.out.println(MyStringUtils.centre("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>", 100));
				System.exit(0);
			}

			// ADD //
			else if (words[0].equalsIgnoreCase("add") || words[0].equalsIgnoreCase("a") && words.length == 4) {
				int x = Integer.parseInt(words[2]);
				int y = Integer.parseInt(words[3]);

				if (x < 0 || x > 4 && y < 0 || y > 7) {
					System.out.println("Invalid position");
				} else if (!words[1].equals("p") && !words[1].equals("s") && !words[1].equals("peashooter")
						&& !words[1].equals("sunflower")) {
					System.out.println("Invalid object");
				} else {
					if (words[1].equalsIgnoreCase("peashooter") || words[1].equalsIgnoreCase("p")) {// m.equalsIgnoreCase
						if (game.getSunCoins() >= 50) {
							Peashooter psh = new Peashooter(x, y);
							game.addPeashooter(psh);
						} else
							System.out.println("You don't have enough suncoins");
					} else if (words[1].equalsIgnoreCase("sunflower") || words[1].equalsIgnoreCase("s")) { // m.equalsIgnoreCase
						if (game.getSunCoins() >= 20) {
							Sunflower snf = new Sunflower(x, y);
							game.addSunflower(snf);
						} else
							System.out.println("You don't have enough suncoins!" + "\n");
					}

					if (game.getZombiesLeft().randomZombies() && game.getZombiesLeft().getRemainingZ() > 0) {
						Zombie zmb = new Zombie(game.getRandomX(), 7);
						game.addZombie(zmb);
					}

					this.game.setContCiclos(this.game.getContCiclos() + 1);
					game.update();
				}
				System.out.println(game.draw());
				System.out.println(this.game.getGamePrinter().toString());
			}

			// LIST //
			else if (words[0].equalsIgnoreCase("list") || words[0].equalsIgnoreCase("l")) {
				System.out.println(this.game.showPlants() + "\n");
			}

			// NONE //
			else if (words[0].equalsIgnoreCase("none") || words[0].equalsIgnoreCase("n")) {
				if (game.getZombiesLeft().randomZombies() && game.getZombiesLeft().getRemainingZ() > 0) {
					Zombie zmb = new Zombie(game.getRandomX(), 7);
					game.addZombie(zmb);
				}

				this.game.setContCiclos(this.game.getContCiclos() + 1);
				game.update();
				System.out.println(game.draw());
				System.out.println(this.game.getGamePrinter().toString());
			} else {
				System.out.println("Unknow Command.");
			}
		}

		// Comprobacion de si el juego ha terminado
		if (game.isFin()) {
			if (!game.isLoseGame())
				System.out.println("Player wins!");
			else {
				System.out.println("Zombies win...\n");
				System.out.println(MyStringUtils.centre("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>", 100));
				//
				System.exit(0);
			}
		}

	}
}
