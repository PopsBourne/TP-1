package tp.pr3;

//representa las posiciones del tablero
public class Position {
	private int x;
	private int y;

	/**Constructor.
	 @param x
	 @param y
	*/
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position neighbour(Direction dir, int _boardSize){
		Position res = null;
		if(valida(_boardSize)) {
			switch(dir) {
			case UP:
			res = new Position (this.x - 1, this.y); break;
			case DOWN:
			res = new Position (this.x + 1, this.y); break;
			case LEFT:
			res = new Position (this.x, this.y - 1); break;
			case RIGHT:
			res = new Position (this.x, this.y + 1); break;
			}
			if(res.valida(_boardSize))
				return res;
			else return null;
		}
		return res;
	}

	  public boolean valida(int bs) {
		  if(x >= bs || y >= bs || x < 0 || y < 0) return false;
		  return true;
	  }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}