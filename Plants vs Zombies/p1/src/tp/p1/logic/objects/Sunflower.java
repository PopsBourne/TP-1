package tp.p1.logic.objects;

import tp.p1.logic.Game;

public class Sunflower {

	private int x, y;
	private int harm;
	private int healthPoints;
	private int cost;
	private int ciclos;

	private int frequency;
	private int numSuns;

	public Sunflower(int x, int y) {
		this.x = x;
		this.y = y;
		this.harm = 0;
		this.healthPoints = 1;
		this.cost = 20;
		this.frequency = 2;
		this.ciclos=0;
	}


	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public String toString() {
		String s = "";
		s += "[S]unflower: Cost: " + this.cost + " suncoins  Harm: " + this.harm;
		return s;
	}
	
	public String icon() {
		return "S["+this.healthPoints+"]";
	}
	
	// Se generan 20 soles cada 2 ciclos
	public void update(Game game) {
		if (this.ciclos > 0 && this.ciclos % 2 == 0)
				game.addSunCoins(20);
		this.ciclos++;
		
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


	public int getCiclos() {
		return ciclos;
	}


	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}
	
	
}
