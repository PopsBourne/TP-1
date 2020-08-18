package tp.p3.logic.board;

import tp.p3.logic.Game;

public class DebugPrinter extends BoardPrinter {

	int dimY;
	private int pos;

	public DebugPrinter(Game game, int dimY) {
		super(game, dimY, 0);
		board = new String[0][32];
		this.pos = 0;
	}

	@Override
	public void encodeGame(Game game) {
		game.debugInfo();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				if (game.checkPosPln(i, j) != " ") {
					board[0][pos] = game.checkPosPln(i, j);
					pos++;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				if (game.checkPosZmb(i, j) != " ") {
					board[0][pos] = game.checkPosZmb(i, j);
					pos++;
				}
			}
		}

		System.out.println(this.printGame(game));

	}

	@Override
	public String printGame(Game game) {
		int tam = game.getTamListPln() + game.getTamListZmb();
		return boardtoString(18, board, tam, 1);
	}

}
