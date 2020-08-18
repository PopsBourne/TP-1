package tp.pr1;
//NO METODOS SET!!!!!!!!!
//representa los resultados de la ejecucion de un movimiento
public class MoveResults {
	private boolean moved;
	private int points;
	private int maxToken;
	
	public MoveResults(boolean moved, int points, int maxToken) {
		this.moved = moved;
		this.points = points;
		this.maxToken = maxToken;
	}

	public boolean isMoved() {
		return moved;
	}
	public int getPoints() {
		return points;
	}
	public int getMaxToken() {
		return maxToken;
	}
}
