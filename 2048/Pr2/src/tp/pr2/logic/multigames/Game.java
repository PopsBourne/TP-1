package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.Board;
import tp.pr2.Direction;
import tp.pr2.MoveResults;

//representa el estado de una partida
public class Game {
	private static final String NEWLINE = System.getProperty("line.separator");
	private Board _board;
	private int _size;
	private int _initCells;
	private long currentSeed;
	private boolean changed;

	private GameStateStack undoStack = new GameStateStack();
	private GameStateStack redoStack = new GameStateStack();
	private GameRules currentRules;
	
	private int highest;
	private int score;
	private boolean finalizado;
	
	/**Constructor.
	  @param rules
	  @param seed
	  @param dim
	  @param initCells
	*/
	public Game(GameRules rules, long seed, int dim, int initCells) {
		currentRules = rules;
		currentSeed = seed;
		_size = dim;
		_initCells = initCells;
		score = 0;
		reset();
	}

	// ejecuta un mov en la dir del tablero, actualizando Score y Highest
	public void move(Direction dir) {
		GameState gs = getState();
		undoStack.push(gs);
		redoStack.clearStack();
		
		MoveResults result = _board.executeMove(dir,currentRules);
		score += result.getPoints();
		highest = currentRules.getWinValue(_board); // quitar y poner board  get max o get min
		changed = result.isMoved();

		if (currentRules.win(_board)) {
			System.out.println(toString());
			String n = NEWLINE;
			System.out.println(n);
			System.out.println("W  E  L  L       D  O  N  E !!");
			finalizado = true;
		} else if (!finalizado() && changed) { 
			finalizado = false;
			currentRules.addNewCell(_board, new Random(currentSeed));
			System.out.println(toString());
		}
		else System.out.println(toString());

		if (currentRules.lose(_board)) {
			finalizado = true;
			String a = NEWLINE;
			System.out.println(a);
			System.out.println("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>");
		}
	}
	// inicializa la partida actual, size-initcell-random son los mismos porq es
	// la = configuracion
	public void reset() { //final
		finalizado = false;
		changed = false;
		score = 0;
		_board = currentRules.createBoard(_size);
		Random rand = new Random(currentSeed);
		currentRules.initBoard(_board, _initCells, rand); // genera initCells celdas nuevas aleatorias
		undoStack.clearStack();
		redoStack.clearStack();
		highest = currentRules.getWinValue(_board);
		System.out.println(this.toString());
	
	}

	public String toString() {
		String s = _board.toString();
		s += NEWLINE;
		String patron = "  best value: %d      score:  %d";
		String resultado = String.format(patron, highest, score);
		s += resultado;
		return s;
	}

	public boolean finalizado() {
		if(currentRules.lose(_board))
			finalizado = true;
		return finalizado;
	}

	public void redo(){
		if(redoStack.getContador() >= 1) {
			undoStack.push(getState()); // push estado actual en undostack  
			GameState last = redoStack.pop(); //extraer la cima de redostack
			setState(last); // ponemos el estado al juego
			System.out.println("Redoing one move ...");
			System.out.println(toString());
		}
		else
			System.out.println("Nothing to redo");
	}
	
	public void undo(){
		if(undoStack.getContador() >= 1) {
			redoStack.push(getState()); // push estado actual en redostack  
			GameState last = undoStack.pop(); //extraer la cima de undostack
			setState(last); // ponemos el estado al juego
			System.out.println("Undoing one move ...");
			System.out.println(toString());
		}
		else 
			System.out.println("Undo is not avalaible");
	}
	
	/**Devuelve el estado actual del juego invocando el método getState de Board.
	  @return GameState
	*/
	public GameState getState(){
		GameState gs = new GameState();
		gs.setScore(score);
		gs.setState(_board.getState());
		return gs;
	}
	
	/**Restablece el juego al estado aState e invocando el método setState de Board.
	  @param aState
	*/
	public void setState(GameState aState){
		score = aState.getScore();
		highest = currentRules.getWinValue(_board);
		_board.setState(aState.getState());
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public long getCurrentSeed() {
		return currentSeed;
	}
	public void setCurrentSeed(long currentSeed) {
		this.currentSeed = currentSeed;
	}
	//muestra el string help
	public void showHelp(String s) {
		System.out.println(s);
	}
	public int get_size() {
		return _size;
	}
	public void set_size(int _size) {
		this._size = _size;
	}
	public int get_initCells() {
		return _initCells;
	}
	public void set_initCells(int _initCells) {
		this._initCells = _initCells;
	}
	public GameRules getCurrentRules() {
		return currentRules;
	}
	public void setCurrentRules(GameRules currentRules) {
		this.currentRules = currentRules;
	}
	/* public static void main(String args[]) { Game g = new Game(4, 3, new Random()); } */
}