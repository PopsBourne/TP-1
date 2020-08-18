package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.Board;
import tp.pr3.Cell;
import tp.pr3.Position;
import tp.pr3.util.MyMathsUtil;

public class RulesFib implements GameRules{
	private static final int STOP_VALUE = 8;

	public void addNewCellAt(Board board, Position pos, Random rand) {
		int num;
		int aleatorio = rand.nextInt(10);
		if (aleatorio == 9) 
			num = 2 ;
		else
			num = 1;
		board.setCell(pos,num);
		
	}

	public int merge(Cell self, Cell other) {
		int i = MyMathsUtil.nextFib(self.getValor());
		int j = MyMathsUtil.nextFib(other.getValor());
		if (other.getValor() == i || self.getValor() == j || 
		   (self.getValor() == other.getValor()) && self.getValor() == 1) {
			self.setValor(self.getValor() + other.getValor());
			other.setValor(0);
			return self.getValor();
		} else
			return 0;
	}

	public int getWinValue(Board board) {
		return board.getMaxValue();
	}

	public boolean win(Board board) {
		if (getWinValue(board) == STOP_VALUE) 
			return true;
		else 
			return false;
	}
}