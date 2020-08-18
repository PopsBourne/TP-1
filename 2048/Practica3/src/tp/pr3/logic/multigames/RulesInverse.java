package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.Board;
import tp.pr3.Cell;
import tp.pr3.Position;

public class RulesInverse implements GameRules{
	private static final int STOP_VALUE = 1;

	public void addNewCellAt(Board board, Position pos, Random rand) {
		int num;
		int aleatorio = rand.nextInt(10);
		if (aleatorio == 9) 
			num = 1024 ;
		else
			num = 2048;
		board.setCell(pos,num);
	}

	public int merge(Cell self, Cell other) {
		int puntuacion;
		if (self.getValor() == other.getValor() && self.getValor() != 0) {
			self.setValor(other.getValor() / 2);
			other.setValor(0);
			puntuacion = (2048 / self.getValor());
			return puntuacion;
		} else 
			return 0;		
	}

	public int getWinValue(Board board) {
		return board.getMinValue();
	}

	public boolean win(Board board) {
		if (getWinValue(board) == STOP_VALUE) 
			return true;
		else 
			return false;
	}
}