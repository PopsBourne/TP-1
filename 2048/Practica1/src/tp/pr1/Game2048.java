package tp.pr1;

import java.util.Random;
import java.util.Scanner;

//contiene el metodo MAIN
public class Game2048 {
	
	public static void main(String args[]) {
		int size = 0;
		int numInitial = 0;
		long seed = 0;
		
		if(args.length == 3){
			size = Integer.parseInt(args[0]);
			numInitial = Integer.parseInt(args[1]);
			seed = Long.parseLong(args[2]);
		}
		else{
			if ( args.length == 2){
				size = Integer.parseInt(args[0]);
				numInitial = Integer.parseInt(args[1]);
				seed = new Random().nextInt(1000);
			}
				else{
					System.out.println("Error en el número de argumentos!!!!!!");
					System.exit(-1);
				}	
		}
		Random r = new Random(seed);
		Game gm = new Game(size, numInitial, r);
		Scanner sc = new Scanner(System.in);
		Controller cntrl = new Controller(gm, sc);
		cntrl.run();
	}
}