package tp.p3.logic;

public class SuncoinManager {

	int coins;
	
	public SuncoinManager() {
	this.coins=50;	
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins += coins;
	}
	
	public void resetSunCoins(int coins) {
		this.coins=coins;
	}
}
