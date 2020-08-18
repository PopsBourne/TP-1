package tp.p2.logic.objects.plants;

import tp.p2.logic.Game;

public class Wallnut extends Plant {

	private int ciclos;
	private int tiempoCiclo;

	public Wallnut(int x, int y) {
		super(x,y);
		this.harm = 0;
		this.healthPoints = 10;
		this.cost = 50;
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
		s += "[N]uez: Cost: " + this.cost + " suncoins  Harm: " + this.harm;
		return s;
	}

	public String debugList() {
		String s = "";
		s += "N[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "]";
		return s;
	}

	public void update(Game game) {
		this.ciclos++;
	}

	public boolean isSDead() {
		if (this.getHealthPoints() < 1)
			return true;
		else
			return false;
	}

	public String icon() {
		return "N[" + this.healthPoints + "]";
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	// quitar
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	// quitar
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public int getCiclos() {
		return this.ciclos;
	}
}
