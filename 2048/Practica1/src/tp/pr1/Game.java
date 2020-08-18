package tp.pr1;

import java.util.Random;

//representa el estado de una partida
public class Game {
	private static final String NEWLINE = System.getProperty("line.separator");
	private Board _board;
	private int _size;
	private int _initCells;
	private Random _myRandom;
	
	private int highest;
	private int score;
	private boolean finalizado;

	//construyo el tablero con la configuracion inicial
	public Game(int _size, int _initCells, Random _myRandom) {
		this._size = _size;
		this._initCells = _initCells;
		this._myRandom = _myRandom;
		score = 0;
		finalizado = false;
		_board = new Board(_size);
		llenarCeldas();
		System.out.println(this.toString());
		
	}
	
	public void generaCelda( boolean firstTime){
			int n;
			int aleatorio = _myRandom.nextInt(10); //genero aleatorio entre 0 y 9, del [0,8](90%) = 2, el 9(10%) = 4
			if(aleatorio == 9){
				if (firstTime)
					highest = 4;
				n = 4;
			}
			else n = 2;
			int p = _myRandom.nextInt(_size);
			int q = _myRandom.nextInt(_size);
			Position posic = new Position(p, q);
			while(!_board.getCell(posic).isEmpty()){
				p = _myRandom.nextInt(_size);
				q = _myRandom.nextInt(_size);
				posic.setX(p);
				posic.setY(q);
			}
			_board.setCell(posic, n);
	}
	
	//genera nums aleatorios iniciales
	private void llenarCeldas(){
		highest = 2;
		for(int i = 0; i < _initCells; i++){
			generaCelda(true);
		}
	}
	
	//ejecuta un mov en la dir del tablero, actualizando Score y Highest
	public void move(Direction dir){
		MoveResults result = _board.executeMove(dir);
		/*Position pos = new Position(0,0);
		for(int i=0; i< _size;i++){
			for(int j=0; j <_size; j++){
				pos.setX(i);
				pos.setY(j);
				if(highest < _board.getCell(pos).getValor())
				 highest = _board.getCell(pos).getValor();
				}
			}*/
		score += result.getPoints();
		highest = result.getMaxToken();
	}
	
	//inicializa la partida actual, size-initcell-random son los mismos porq es la = configuracion
	public void reset(){
		finalizado = false;
		score = 0;
		_board = new Board(_size);
		llenarCeldas();
	}
	
	public String toString(){
		String s = _board.toString();
		s += NEWLINE;
		String patron = "  highest: %d      score:  %d";
		String resultado = String.format(patron, highest, score);
		s += resultado;
		/*String s = _board.toString();
		s += NEWLINE;
		s += "  highest: " + highest + "      score: " + score;
		*/
		return s;
	}
	
	public boolean canMerge(){
		boolean bool = false;
		Board clon = new Board(_size);
		for(int i = 0; i < _size; i++)
			for(int j = 0; j < _size; j++){
				Position p = new Position(i,j);
				clon.getCell(p).setValor(_board.getCell(p).getValor());
			}
		//arriba
			clon.rotarMatrizDcha();
			bool |= clon.mergeDesplaz();
		//abajo
			for(int i = 0; i < _size; i++)
				for(int j = 0; j < _size; j++){
					Position p = new Position(i,j);
					clon.getCell(p).setValor(_board.getCell(p).getValor());
				}
			
			for(int i = 0; i < 3; i++) 
				clon.rotarMatrizDcha();
			bool |= clon.mergeDesplaz();
		//izquierda
			for(int i = 0; i < _size; i++)
				for(int j = 0; j < _size; j++){
					Position p = new Position(i,j);
					clon.getCell(p).setValor(_board.getCell(p).getValor());
				}

			for(int i = 0; i < 2; i++) {
				//System.out.println(this.toString());
				clon.rotarMatrizDcha();
			}
			bool |=  clon.mergeDesplaz();
		//derecha
			for(int i = 0; i < _size; i++)
				for(int j = 0; j < _size; j++){
					Position p = new Position(i,j);
					clon.getCell(p).setValor(_board.getCell(p).getValor());
				}
			bool |= clon.mergeDesplaz();
			return bool;
	}
	
	public boolean finalizado(){
		if (!_board.freeCell() && !canMerge())
			finalizado = true;
		return finalizado;
	}
	
	public boolean lleno(){
		if (!_board.freeCell())
			return  true;
		return false;
	}

	public int getHighest() {
		return highest;
	}

	public void setHighest(int highest) {
		this.highest = highest;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	/*public static void main(String args[]) {
		Game g = new Game(4, 3, new Random());
	} */
}