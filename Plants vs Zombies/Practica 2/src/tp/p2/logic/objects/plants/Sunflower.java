package tp.p2.logic.objects.plants;

import tp.p2.logic.Game;

public class Sunflower extends Plant{

	private int ciclos;
	private int tiempoCiclo;
	private int frequency;


	public Sunflower(int x, int y) {
		super(x,y);
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
	public String debugList() {
		String s = "";
		s += "S[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "]";
		return s;
	}
	
	public String icon() {
		return "S["+this.healthPoints+"]";
	}
	
	// Se generan 20 soles cada 2 ciclos
	public void update(Game game) {
		if (this.ciclos > 0 && this.ciclos % 2 == 0)
				game.setSunCoins(game.getSunCoins()+20);
		this.ciclos++;
	}
	
	public boolean isSDead() {
		if (this.getHealthPoints() < 1)
			return true;
		else
			return false;
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
	
	
	@Override
	public int getCost() {
		return this.cost;
	}

}
