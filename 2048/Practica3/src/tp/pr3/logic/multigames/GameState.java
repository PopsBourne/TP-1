package tp.pr3.logic.multigames;

public class GameState {
	private int score;
	private int[][] boardState;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int[][] getState() {
		return boardState;
	}
	public void setState(int[][] aState) {
		this.boardState = aState;
	}
}