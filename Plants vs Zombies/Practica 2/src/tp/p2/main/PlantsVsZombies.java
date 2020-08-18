package tp.p2.main;

import java.util.Random;
import java.util.Scanner;
import tp.p2.control.Controller;
import tp.p2.logic.Game;
import tp.p2.logic.Level;

public class PlantsVsZombies {

	public static void main(String[] args) {
		String level = String.valueOf(args[0]).toUpperCase();
		long seed = 0;
		int numZ = 0;
		double frq = 0;

		// Comprobacion de numero de parametros
		if (!(args.length == 1) && !(args.length == 2)) {
			System.out.println("Error en el numero de parametros. Saliendo del programa");
			System.exit(0);
		} else {

			// Comprobacion del LEVEL mediante clase ENUM
			if (level.equals(Level.EASY.toString())) {
				numZ = 3;
				frq = 0.1;
			} else if (level.equals(Level.HARD.toString())) {
				numZ = 5;
				frq = 0.2;
			} else if (level.equals(Level.INSANE.toString())) {
				numZ = 10;
				frq = 0.3;
			} else {
				System.out.println("Error en el parametro LEVEL");
			}

			// Comprobacion del parametro de la semilla
			if (args.length == 2) {
				seed = Long.parseLong(args[1]);

			} else {
				if (args.length == 1)
					seed = new Random().nextInt(1000);
			}
		}

		// Parametros Limpios
		Random rand = new Random(seed);
		System.out.println("Random seed used: " + seed);
		Scanner sc = new Scanner(System.in);
		Controller ctrl = new Controller(new Game(rand, numZ, frq), sc);
		ctrl.run();

	}
}
