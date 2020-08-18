package tp.p1.logic;

import tp.p1.util.MyStringUtils;

public class GamePrinter {
	int dimX; 
	int dimY;
	String[][] board;
	final String space = " ";
	
	
	public GamePrinter(Game game, int dimX, int dimY) {
		this.dimX = dimX;
		this.dimY = dimY;
		board = new String[dimX][dimY];
		encodeGame(game);
	}
	
	private void encodeGame(Game game) {
		System.out.println(game.draw());
		
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {			
				board[i][j] = space;
			}
		}
		System.out.println(this.toString());
	}
	
	
	public String toString() {

		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(int x, int y, String icn) {
		this.board[x][y] = icn;
	}
	
	
}