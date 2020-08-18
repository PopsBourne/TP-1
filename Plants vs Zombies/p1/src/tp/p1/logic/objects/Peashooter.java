package tp.p1.logic.objects;

import tp.p1.logic.Game;

public class Peashooter {

	private int x, y; // posicion
	private int harm;
	private int healthPoints;
	private int resistance;
	private int frq;// cambiar nombre
	private int cost;
	private int numSuns;
	private int ciclos;

	

	private Game game;

	public Peashooter(int x, int y) {
		this.x = x;
		this.y = y;
		this.harm = 1;
		this.healthPoints = 3;
		this.frq = 1;
		this.numSuns = 10;
		this.cost = 50;
		this.resistance = 3;
		this.ciclos = 0;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public String toString() {
		String s = "";
		s += "[P]eashooter: Cost: " + this.cost + " suncoins  Harm: " + this.harm;
		return s;
	}

	public void update(Game game) {
		if (this.ciclos >= 1) {
			game.peashooterShoots(harm, game.getContCiclos());
			setCiclos(0);
		}
		this.ciclos++;
	}


	public String icon() {
		return "P[" + this.healthPoints + "]";
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

	public int getHarm() {
		return harm;
	}

	public void setHarm(int harm) {
		this.harm = harm;
	}
	

}
