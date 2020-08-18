package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.Board;
import tp.pr2.Cell;
import tp.pr2.Position;

public class Rules2048 implements GameRules{
	private static final int STOP_VALUE = 2048;

	public void addNewCellAt(Board board, Position pos, Random rand) {
		int num;
		int aleatorio = rand.nextInt(10); // genero aleatorio entre 0 y 9,del [0,8](90%) = 2, el 9(10%) = 4
		if (aleatorio == 9) 
			num = 4;
		else
			num = 2;
		board.setCell(pos,num);
	}

	public int merge(Cell self, Cell other) {
		if(self.getValor() == other.getValor() && self.getValor() != 0){
			self.setValor( self.getValor() + other.getValor());
			other.setValor(0);// borro el valor de la casilla neighbour
			return self.getValor();
		}
		else
			return 0;
	}

	public int getWinValue(Board board) {
		return board.getMaxValue();
	}

	public boolean win(Board board) {
		if (getWinValue(board) == STOP_VALUE) 
			return true;
		else return false;
	}
}