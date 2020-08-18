package tp.p3.logic.board;

import tp.p3.logic.Game;
import tp.p3.util.MyStringUtils;

public abstract class BoardPrinter implements GamePrinter {

	protected String[][] board;
	int dimX;
	int dimY;
	final String space = " ";

	public BoardPrinter(Game game, int dimX, int dimY) {
		super();
		this.dimX = dimX;
		this.dimY = dimY;
		board = new String[4][32];
		encodeGame(game);
	}

	public abstract void encodeGame(Game game);

	public String boardtoString(int cellSize, String[][] board, int dimY, int dimX) {
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";

		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineDelimiter);

		for (int i = 0; i < dimX; i++) {
			str.append(margin).append(vDelimiter);
			for (int j = 0; j < dimY; j++) {
				str.append(MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
			}
			str.append(lineDelimiter);
		}
		return str.toString();
	}
}
