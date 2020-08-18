package tp.pr2;
//NO METODOS SET!!!!!!!!!
//representa los resultados de la ejecucion de un movimiento
public class MoveResults {
	private boolean moved;
	private int points;
	
	/**Constructor.
	 @param moved
	 @param points
	*/
	public MoveResults(boolean moved, int points) {
		this.moved = moved;
		this.points = points;
	}

	public boolean isMoved() {
		return moved;
	}
	
	public int getPoints() {
		return points;
	}
}