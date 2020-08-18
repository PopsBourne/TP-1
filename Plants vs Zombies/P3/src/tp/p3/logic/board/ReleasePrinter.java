package tp.p3.logic.board;

import tp.p3.logic.Game;

public class ReleasePrinter extends BoardPrinter {

	public ReleasePrinter(Game game, int dimX, int dimY) {
		super(game, dimX, dimY);
	}

	@Override
	public void encodeGame(Game game) {
		System.out.println(game.draw());

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				if (game.checkPosLists(i, j) != " ")
					board[i][j] = game.checkPosLists(i, j);
				else
					board[i][j] = " ";
			}
		}

		System.out.println(this.printGame(game));
	}

	public String printGame(Game game) {
		return boardtoString(7, board, 8, 4);
	}

}
