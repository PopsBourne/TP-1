package tp.pr3;

import tp.pr3.Cell;
import tp.pr3.logic.multigames.GameRules;

//representa cada una de las baldosas del tablero
public class Cell {
	private int valor;
	
	/**Constructor.
	  @param valor
	*/
	public Cell(int valor) {
		this.valor = valor;
	}

	//comprueba si una baldosa esta vacia
	public boolean isEmpty(){
		if(valor == 0) return true;
		else return false;
	}
	
	//fusiona dos baldosas en una
	public int doMerge(Cell neighbour, GameRules rules){
		return rules.merge(this, neighbour);
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}