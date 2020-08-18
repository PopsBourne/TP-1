package tp.pr1;

import tp.pr1.util.MyStringUtils;

//almacena el estado de un tablero y tiene metodos para su gestion
public class Board {
	private static final String NEWLINE = System.getProperty("line.separator");
	private Cell [][] _board;
	private int _boardSize;
	private int puntos;
	
	//inicializamos el tablero con su dimension todo a 0
	public Board(int _boardSize) {
		this._boardSize = _boardSize;
		_board = new Cell [_boardSize][_boardSize];
		
		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				_board[i][j]= new Cell(0);
	}

	//ejecuta las 2 primeras acciones de un movimiento, desplazar y fusionar, en la direccion dir
	public MoveResults executeMove(Direction dir){
		//logica principal del movimiento
		boolean bool = false;
		puntos = 0;
		int maximo = 0;
		switch (dir) {
		case UP: 
			rotarMatrizDcha();
			bool = mergeDesplaz();
			for(int i = 0; i < 3; i++) 
				rotarMatrizDcha();
			break;
		case DOWN:
			for(int i = 0; i < 3; i++) 
				rotarMatrizDcha();
			bool = mergeDesplaz();
			rotarMatrizDcha();
			break;
		case LEFT:
			for(int i = 0; i < 2; i++) {
				//System.out.println(this.toString());
				rotarMatrizDcha();
			}
			bool = mergeDesplaz();
			for(int i = 0; i < 2; i++) 
				rotarMatrizDcha();
			break;
		case RIGHT:
			bool = mergeDesplaz();
			break;
		default: System.out.println("Direccion erronea!"); break;
		}
		maximo = maxToken();
		MoveResults mvrslt = new MoveResults(bool, puntos, maximo);
	return mvrslt;
	}
	
	//devuelve el booleano q indica si hubo un domerge o un desplaz
	public boolean mergeDesplaz(){
		boolean cambio = false;
		boolean b = false;
		
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
				b = _board[i][j].doMerge(_board[pos2.getX()][pos2.getY()]);
					if(b){
					cambio = true;
					puntos += _board[i][j].getValor();
					}
				}
			}
			//metodo de la burbuja de final a principio con 0s
			for(int fin = 0; fin < _boardSize; fin++){
				for(int l = _boardSize - 1; l > 0 + fin; l--){
					if(_board[i][l].isEmpty()){
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
	
	//devuelve el maximo valor del tablero 
	public int maxToken(){
		int max = 0;
		for(int i = 0; i < _boardSize; i++){
			for(int j = 0; j < _boardSize; j++){
				if(_board[i][j].getValor() > max)
					max = _board[i][j].getValor();
			}
		}
		return max;
	}
	
	// comprueba que el tablero esta lleno
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
				temporal += MyStringUtils.centre(_board[i][j].getValor()+"", cellSize);
			}
			temporal += vDelimiter;
			temporal += NEWLINE;
		}
		temporal += space;
		temporal += MyStringUtils.repeat(hDelimiter, cellSize*_boardSize+_boardSize-1);
		return temporal;	
	}
	
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
	
	/*public static void main(String args[]) {
		Board k = new Board(5);
		System.out.println(k.toString());
		Cell c1 = new Cell(0);
		Cell c2 = new Cell(0);
		c1.setValor(8);
		c2.setValor(4);
		k._board[0][0] = c1;
		k._board[0][4] = c2;
		System.out.println(k.toString());
		MoveResults m = k.executeMove(Direction.RIGHT);
		System.out.println(k.toString());	
	} */
}