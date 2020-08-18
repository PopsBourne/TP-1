package tp.pr2;

import tp.pr2.util.MyStringUtils;
import tp.pr2.logic.multigames.GameRules;

public class Board {
	private static final String NEWLINE = System.getProperty("line.separator");
	private Cell [][] _board;
	private int _boardSize;
	private int puntos;

	/**Constructor.
	  @param _boardSize
	*/
	public Board(int _boardSize) {
		this._boardSize = _boardSize;
		_board = new Cell [_boardSize][_boardSize];

		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				_board[i][j]= new Cell(0);
	}
	
	/**Ejecuta las 2 primeras acciones de un movimiento, desplazar y fusionar, en la direccion dir.
	  @param dir
	  @param rules
	  @return devuelve el MoveResults con sus puntos e indicando si hubo mergedesplaz
	*/
	public MoveResults executeMove(Direction dir, GameRules rules){
		//logica principal del movimiento
		boolean bool = false;
		puntos = 0;

		switch (dir) {
		case UP:
			rotarMatrizDcha();
			bool = mergeDesplaz(rules);
			for(int i = 0; i < 3; i++)
				rotarMatrizDcha();
			break;
		case DOWN:
			for(int i = 0; i < 3; i++)
				rotarMatrizDcha();
			bool = mergeDesplaz(rules);
			rotarMatrizDcha();
			break;
		case LEFT:
			for(int i = 0; i < 2; i++) {
				//System.out.println(this.toString());
				rotarMatrizDcha();
			}
			bool = mergeDesplaz(rules);
			for(int i = 0; i < 2; i++)
				rotarMatrizDcha();
			break;
		case RIGHT:
			bool = mergeDesplaz(rules);
			break;
		default: System.out.println("Wrong direction!"); break;
		}
		MoveResults mvrslt = new MoveResults(bool, puntos);
	return mvrslt;
	}
	
	/**Comprueba si hace un domerge o un desplazamiento.
	  @param rules
	  @return true si hubo domerge o un desplazamiento
	*/
	public boolean mergeDesplaz(GameRules rules){
		boolean cambio = false;
		//boolean b = false;
		int b = 0;

		for(int i = 0; i < _boardSize; i++){
			//metodo de la burbuja al principio para quitar 0 entre los num
			for(int f = 0; f < _boardSize; f++){
				for(int ll = _boardSize - 1; ll > 0 + f; ll--){
					if(_board[i][ll].isEmpty() && !_board[i][ll - 1].isEmpty()){
						Cell c = _board[i][ll];
						_board[i][ll] = _board[i][ll-1];
						_board[i][ll-1] = c;
						cambio = true;
					}
				}
			}
			for(int j = _boardSize - 1; j >= 0; j--){
				Position pos = new Position(i, j);
				Position pos2 = pos.neighbour(Direction.LEFT, _boardSize);
				if(pos2 != null){
				b = _board[i][j].doMerge(_board[pos2.getX()][pos2.getY()],rules);
					if(b != 0){
					cambio = true;
					//puntos += _board[i][j].getValor();
					puntos += b;
					}
				}
			}
			//metodo de la burbuja de final a principio con 0s
			for(int fin = 0; fin < _boardSize; fin++){
				for(int l = _boardSize - 1; l > 0 + fin; l--){
					if(_board[i][l].isEmpty() && !_board[i][l - 1].isEmpty()){
						Cell c = _board[i][l];
						_board[i][l] = _board[i][l-1];
						_board[i][l-1] = c;
						cambio = true;
					}
				}
			}
		}
		return cambio;
	}

	/**Rota la matriz a la derecha.
	*/
	public void rotarMatrizDcha() {
		Cell [][] aux = new Cell[_boardSize][_boardSize];
		for(int f = 0; f < _boardSize; f++) {
			for(int c = 0; c < _boardSize; c++) {
				aux[f][c]=_board[f][c];
			}
		}
		int j = 0;
		for(int f = 0; f < _boardSize; f++) {
			int i =_boardSize - 1;
			for(int c = 0; c < _boardSize; c++) {
				_board[f][c] = aux[i][j];
				//System.out.println(this.toString());
				i--;
			}
			j++;
		}
	}

	/**Comprueba si hay alguna celda libre en el tablero.
	  @return un boolean a true si hay alguna celda libre en el tablero
	*/
	public boolean freeCell(){
		boolean libre = false;
		for(int i = 0; i < _boardSize && !libre;i++){
			for(int j = 0; j < _boardSize && !libre;j++){
				if ( _board[i][j].isEmpty())
					libre = true;
			}
		}
		return libre;
	}

	/**Junta el tablero y su información para poder imprimirlo por pantalla.
	  @return un String del tablero junto a su best value y score
	*/
	public String toString(){
		int cellSize = 7;
		String space = " ";
		String vDelimiter = "|";
		String hDelimiter = "-";
		String temporal="";
		for(int i = 0; i < _boardSize; i++){
			temporal += space;
			temporal += MyStringUtils.repeat(hDelimiter, cellSize*_boardSize+_boardSize-1);
			temporal += NEWLINE;
			for(int j = 0; j < _boardSize; j++){
				temporal += vDelimiter;
				if(_board[i][j].getValor()!=0)
				temporal += MyStringUtils.centre(_board[i][j].getValor()+"", cellSize);
				else temporal += MyStringUtils.centre("", cellSize);

			}
			temporal += vDelimiter;
			temporal += NEWLINE;
		}
		temporal += space;
		temporal += MyStringUtils.repeat(hDelimiter, cellSize*_boardSize+_boardSize-1);
		return temporal;
	}

	/**Devuelve el estado actual del tablero.
	  @return  un array de enteros (el tablero)
	*/
	public int[][] getState(){
		int[][] copia = new int[_boardSize][_boardSize];
		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				copia[i][j]=_board[i][j].getValor();
		return copia;
	}

	/** Pone el tablero en el estado definido por aState.
	  @param aState
	*/
	public void setState(int[][] aState){
		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				_board[i][j].setValor(aState[i][j]);
	}

	/**Establece.
	  @param pos
	  @param value
	*/
	public void setCell(Position pos, int value){
		_board[pos.getX()][pos.getY()].setValor(value);
	}

	public Cell getCell(Position pos){
		return _board[pos.getX()][pos.getY()];
	}

	public int get_boardSize() {
		return _boardSize;
	}

	public void set_boardSize(int _boardSize) {
		this._boardSize = _boardSize;
	}
	
	public int getMaxValue() {
		int max = 0;
		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				if(_board[i][j].getValor() > max)
					max = _board[i][j].getValor();
		return max;
	}
	
	public int getMinValue() {
		int min = 2048;
		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				if(_board[i][j].getValor() < min && _board[i][j].getValor() != 0)
					min = _board[i][j].getValor();
		return min;
	}
	
	public boolean canMerge(GameRules currentRules) {
		boolean bool = false;
		Board clon = new Board(_boardSize);
		for (int i = 0; i < _boardSize; i++)
			for (int j = 0; j < _boardSize; j++) {
				Position p = new Position(i, j);
				clon.getCell(p).setValor(getCell(p).getValor());
			}
		// arriba
		clon.rotarMatrizDcha();
		bool |= clon.mergeDesplaz(currentRules);
		// abajo
		for (int i = 0; i < _boardSize; i++)
			for (int j = 0; j < _boardSize; j++) {
				Position p = new Position(i, j);
				clon.getCell(p).setValor(getCell(p).getValor());
			}
		for (int i = 0; i < 3; i++)
			clon.rotarMatrizDcha();
		bool |= clon.mergeDesplaz(currentRules);
		// izquierda
		for (int i = 0; i < _boardSize; i++)
			for (int j = 0; j < _boardSize; j++) {
				Position p = new Position(i, j);
				clon.getCell(p).setValor(getCell(p).getValor());
			}
		for (int i = 0; i < 2; i++) {
			// System.out.println(this.toString());
			clon.rotarMatrizDcha();
		}
		bool |= clon.mergeDesplaz(currentRules);
		// derecha
		for (int i = 0; i < _boardSize; i++)
			for (int j = 0; j < _boardSize; j++) {
				Position p = new Position(i, j);
				clon.getCell(p).setValor(getCell(p).getValor());
			}
		bool |= clon.mergeDesplaz(currentRules);
		return bool;
	}
	/*
	public static void main(String args[]) {
		Board k = new Board(5);
		System.out.println(k.toString());
		Cell c1 = new Cell(0);
		Cell c2 = new Cell(0);
		c1.setValor(3000);
		c2.setValor(4);
		k._board[0][0] = c1;
		k._board[0][4] = c2;
		System.out.println(k.toString());
		MoveResults m = k.executeMove(Direction.RIGHT);
		System.out.println(k.toString());
	}
	*/
}