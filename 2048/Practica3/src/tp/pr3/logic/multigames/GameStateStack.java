package tp.pr3.logic.multigames;

import tp.pr3.exceptions.EmptyStackException;

public class GameStateStack {
	private static final int CAPACITY = 20;
	private GameState[] buffer = new GameState[CAPACITY + 1];
	private int contador;
	
	/**Constructor.
	*/
	public GameStateStack() {
		contador = 0;
	}
	
	/**Devuelve el �ltimo estado almacenado.
	  @return el �ltimo GameState de la pila
	*/
	public GameState pop() throws EmptyStackException {
		if(!isEmpty()) {
			contador--;
			return buffer[contador];
		}
		else throw new EmptyStackException("Empty stack");
	}
	
	/**Almacena un nuevo estado.
	  @param state
	*/
	public void push(GameState state) {
		if(!isFull()) {
			buffer[contador] = state;
			contador ++;
		}else {
			for (int i = 0; i < CAPACITY - 1; i ++) 
				buffer[i] = buffer[i+1];
			buffer[CAPACITY - 1] = state;
		}
	}
	
	public GameState getTop() throws EmptyStackException {
		if(contador > 0)
			return buffer[contador - 1];
		else throw new EmptyStackException("Empty stack");
	}
	
	/**Devuelve true si la secuencia est� vac�a.
	  @return true si el contador es 0
	*/
	public boolean isEmpty() {
		if(contador == 0)
			return true;
		else return false;
	}
	
	public boolean isFull() { // Devuelve true si la secuencia est� lleno
		if(contador == CAPACITY)
			return true;
		else return false;
	}
	
	public void clearStack() {
		contador = 0 ;
		buffer = new GameState[CAPACITY + 1];
	}
	
	public int getContador() {
		return contador;
	}
}