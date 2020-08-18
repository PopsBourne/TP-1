package tp.pr1;

//representa cada una de las baldosas del tablero
public class Cell {
	private int valor;
	
	public Cell(int valor) {
		this.valor = valor;
	}

	//comprueba si una baldosa esta vacia
	public boolean isEmpty(){
		if(valor == 0)
			return true;
		else
			return false;
	}
	
	//fusiona dos baldosas en una
	public boolean doMerge(Cell neighbour){
		if(valor == neighbour.getValor() && valor != 0){
			valor = valor + neighbour.getValor(); //en valor siempre guardo la suma de las 2 casillas si son del mismo valor
			neighbour.setValor(0);// borro el valor de la casilla neighbour
			return true;
		}
		else
			return false;
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}