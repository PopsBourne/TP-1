package tp.pr2.logic.multigames;

public class GameStateStack {
	private static final int CAPACITY = 20;
	private GameState[] buffer = new GameState[CAPACITY + 1];
	private int contador;
	
	/**Constructor.
	*/
	public GameStateStack() {
		contador = 0;
	}
	
	/**Devuelve el último estado almacenado.
	  @return el último GameState de la pila
	*/
	public GameState pop() {
		if(!isEmpty()) {
			contador--;
			return buffer[contador];
		}
		return null;
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
	
	public GameState getTop(){
		if(contador > 0)
			return buffer[contador - 1];
		return null;
	}
	
	/**Devuelve true si la secuencia está vacía.
	  @return true si el contador es 0
	*/
	public boolean isEmpty() {
		if(contador == 0)
			return true;
		else return false;
	}
	
	public boolean isFull() { // Devuelve true si la secuencia está lleno
		if(contador == CAPACITY)
			return true;
		else return false;
	}
	
	public void clearStack() { // Devuelve true si la secuencia está lleno
		contador = 0 ;
		buffer = new GameState[CAPACITY + 1];
	}
	
	public int getContador() {
		return contador;
	}
}