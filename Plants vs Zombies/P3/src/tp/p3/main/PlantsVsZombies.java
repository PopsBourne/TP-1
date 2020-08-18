package tp.p3.main;

import java.util.Random;
import java.util.Scanner;

import tp.p3.control.Controller;
import tp.p3.logic.Game;
import tp.p3.logic.Level;

public class PlantsVsZombies {//lanzar excepcion si no hay argumentos

	public static void main(String[] args) {
		long seed = 0;
		Level level = Level.parse(String.valueOf(args[0]).toUpperCase());

		// Comprobacion de numero de parametros
		if (!(args.length == 1) && !(args.length == 2)) {
			System.err.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");
			System.exit(0);
		} else {
			if (level == null) {
				System.err.println(
						"Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
				System.exit(0);
			}
		}

		if (args.length == 2) {
			try {
				seed = Long.parseLong(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number");
				System.exit(0);
			}
		} else {
			if (args.length == 1)
				seed = new Random().nextInt(1000);
		}

		Random rand = new Random(seed);
		System.out.println("Random seed used: " + seed);
		Scanner sc = new Scanner(System.in);
		Controller ctrl = new Controller(new Game(rand, level), sc);
		ctrl.run();
	}
}
