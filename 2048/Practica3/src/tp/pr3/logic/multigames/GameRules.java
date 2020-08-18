package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.Board;
import tp.pr3.Cell;
import tp.pr3.Position;

public interface GameRules {
	
	/**Incorpora una nueva celda con valor aleatorio en la posici�n pos del tablero board.
	  @param board
	  @param pos
	  @param rand
	*/
	void addNewCellAt(Board board, Position pos, Random rand);
	
	/**Fusiona dos celdas y devuelve el n�mero de puntos obtenidos.
	  @param self
	  @param other
	  @return el n�mero de puntos obtenido
	*/
	int merge(Cell self, Cell other);

	/**Devuelve el mejor valor del tablero seg�n las reglas del juego, 
	   comprobando si es un valor ganador y si se ha ganado el juego.
	  @param board
	  @return el mejor valor del tablero
	*/
	int getWinValue(Board board);
	
	/**Devuelve si el juego se ha ganado o no.
	  @param board
	  @return true si ha ganado
	*/
	boolean win(Board board);
	
	/**Devuelve si el juego se ha perdido o no.
	  @param board
	  @return true si ha perdido
	*/
	default boolean lose(Board board) {
		if (!board.freeCell() && !board.canMerge(this) && !win(board))
			return true;
		return false;
	}
	
	/**Crea un tablero size � size.
	  @param size
	  @return un tablero size � size 
	*/
	default Board createBoard(int size) {
		return new Board(size);
	}
	
	/**Elige una posici�n libre de board e invoca el m�todo addNewCellAt() para 
	  a�adir una celda en esa posici�n.
	  @param board
	  @param rand
	*/
	default void addNewCell(Board board, Random rand) {
		int p = rand.nextInt(board.get_boardSize());
		int q = rand.nextInt(board.get_boardSize());
		Position pos = new Position(p, q);
		while (!board.getCell(pos).isEmpty()) {
			p = rand.nextInt(board.get_boardSize());
			q = rand.nextInt(board.get_boardSize());
			pos.setX(p);
			pos.setY(q);
		}
		addNewCellAt(board, pos, rand);
	}
	
	/** Inicializa board eligiendo numCells posiciones libres,
	  e invoca el m�todo addNewCellAt() para a�adir nuevas celdas 
	  en esas posiciones.
	  @param board
	  @param numCells
	  @param rand
	*/
	default void initBoard(Board board, int numCells, Random rand) {
		for (int i = 0; i < numCells; i++) 
			addNewCell(board, rand);
	}
}