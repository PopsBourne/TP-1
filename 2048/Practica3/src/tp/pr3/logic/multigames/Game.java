package tp.pr3.logic.multigames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import tp.pr3.Board;
import tp.pr3.Direction;
import tp.pr3.MoveResults;
import tp.pr3.control.commands.GameType;
import tp.pr3.exceptions.EmptyStackException;
import tp.pr3.exceptions.GameOverException;

//representa el estado de una partida
public class Game {
	private static final String NEWLINE = System.getProperty("line.separator");
	private Board _board;
	private int _size;
	private int _initCells;
	private long currentSeed;
	private Random r;
	private boolean changed;

	private GameStateStack undoStack = new GameStateStack();
	private GameStateStack redoStack = new GameStateStack();
	// private GameRules currentRules;

	private int highest;
	private int score;
	private boolean finalizado;

	private GameType gameType;

	/**
	 * Constructor.
	 * 
	 * @param rules
	 * @param seed
	 * @param dim
	 * @param initCells
	 */
	public Game(GameType gt, long seed, int dim, int initCells) {
		gameType = gt;
		currentSeed = seed;
		_size = dim;
		_initCells = initCells;
		score = 0;
		r = new Random(seed);
		reset();
	}

	// ejecuta un mov en la dir del tablero, actualizando Score y Highest
	public void move(Direction dir) throws GameOverException {
		GameState gs = getState();
		undoStack.push(gs);
		redoStack.clearStack();
		MoveResults result = _board.executeMove(dir, gameType.getRules());
		score += result.getPoints();
		highest = gameType.getRules().getWinValue(_board); // quitar y poner board get max o get min
		changed = result.isMoved();

		if (gameType.getRules().win(_board)) {
			System.out.println(toString());
			finalizado = true;
			throw new GameOverException("W  E  L  L       D  O  N  E !!");

		} else if (!finalizado() && changed) {
			finalizado = false;
			gameType.getRules().addNewCell(_board, r);
			System.out.println(toString());
		} else
			System.out.println(toString());

		if (gameType.getRules().lose(_board)) {
			System.out.println(toString());
			finalizado = true;
			throw new GameOverException("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>");
		}
	}

	// inicializa la partida actual, size-initcell-random son los mismos porq es
	// la = configuracion
	public boolean reset() { // final
		finalizado = false;
		changed = false;
		score = 0;
		_board = gameType.getRules().createBoard(_size);
		Random rand = new Random(currentSeed);
		gameType.getRules().initBoard(_board, _initCells, rand); // genera initCells celdas nuevas aleatorias
		undoStack.clearStack();
		redoStack.clearStack();
		highest = gameType.getRules().getWinValue(_board);
		System.out.println(this.toString());
		return true;
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
		if (gameType.getRules().lose(_board))
			finalizado = true;
		return finalizado;
	}

	public boolean redo() {
		try {
			GameState last = redoStack.pop();// extraer la cima de redostack
			undoStack.push(getState()); // push estado actual en undostack
			setState(last); // ponemos el estado al juego
			System.out.println("Redoing one move ...");
			System.out.println(toString());
			return true;
		} catch (EmptyStackException e) {
			System.out.println("Nothing to redo");
			return false;
		}
	}

	public boolean undo() {
		try {
			GameState last = undoStack.pop(); // extraer la cima de undostack
			redoStack.push(getState()); // push estado actual en redostack
			setState(last); // ponemos el estado al juego
			System.out.println("Undoing one move ...");
			System.out.println(toString());
			return true;
		} catch (EmptyStackException e) {
			System.out.println("Undo is not avalaible");
			return false;
		}
	}

	/**
	 * Devuelve el estado actual del juego invocando el método getState de Board.
	 * 
	 * @return GameState
	 */
	public GameState getState() {
		GameState gs = new GameState();
		gs.setScore(score);
		gs.setState(_board.getState());
		return gs;
	}

	/**
	 * Restablece el juego al estado aState e invocando el método setState de Board.
	 * 
	 * @param aState
	 */
	public void setState(GameState aState) {
		score = aState.getScore();
		highest = gameType.getRules().getWinValue(_board);
		_board.setState(aState.getState());
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public boolean setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
		return true;
	}

	public long getCurrentSeed() {
		return currentSeed;
	}

	public void setCurrentSeed(long currentSeed) {
		this.currentSeed = currentSeed;
	}

	// muestra el string help
	public boolean showHelp(String s) {
		System.out.println(s);
		return true;
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

	public GameRules getcurrentRules() {
		return gameType.getRules();
	}

	public void store(BufferedWriter out) throws IOException {
		try {
			_board.store(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.newLine();
		out.write(_initCells + "\t" + score + "\t");
		out.write(gameType.externalise());
	}

	public GameType load(BufferedReader out) throws IOException, NullPointerException {
		try {
			_board.load(out);
		} catch (IOException e) {
			e.printStackTrace();
			throw new NumberFormatException();
		}
		redoStack.clearStack();
		undoStack.clearStack();
		String s = out.readLine();
		s = out.readLine();
		String[] strings = s.split("\t");
		_initCells = Integer.parseInt(strings[0]);
		score = Integer.parseInt(strings[1]);
		switch (strings[2]) {
		case "original":
			return GameType.ORIG;
		case "fib":
			return GameType.FIB;
		case "inverse":
			return GameType.INV;
		default:
			//throw new NullPointerException();
			return null;
		}
	}

	public void setCurrentRules(GameRules gr) {
		gameType.setRules(gr);
	}

	public void setGameType(GameType gameType2) {
		gameType = gameType2;
	}
}