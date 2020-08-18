package tp.pr2;

import java.util.Random;
import java.util.Scanner;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.multigames.Rules2048;

public class Game2048 {
	
	public static void main(String args[]) {
		int dim = 0;
		int init = 0;
		long seed = 0;
		
		dim = Integer.parseInt(args[0]);
		init = Integer.parseInt(args[1]);
		if(dim < 2) {
			System.out.println("The number of dimension must be more than 2");
			if(init > dim*dim){
				System.out.println("The number of initial cells must be less than the number of cells on the board");
				System.exit(-1);
			}
			System.exit(-1);
		}
		if(init > dim*dim){
			System.out.println("The number of initial cells must be less than the number of cells on the board");
			System.exit(-1);
		}
		if(args.length == 3)
			seed = Long.parseLong(args[2]);
		else if (args.length == 2)
				seed = new Random().nextInt(1000);
		else{
			System.out.println("Error in the number of arguments!!!!!!");
			System.exit(-1);
		}
		Game gm = new Game(new Rules2048(), seed, dim, init);
		Scanner sc = new Scanner(System.in);
		Controller cntrl = new Controller(gm, sc);
		cntrl.run();
	}
}