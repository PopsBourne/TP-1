package tp.p3.logic.objects.plants;

import tp.p3.logic.Game;
import tp.p3.logic.objects.GameObject;

public abstract class Plant extends GameObject {

	protected String plantName;
	protected String firstLetter;
	protected int healthPoints;
	protected int cost;
	protected int harm;
	protected int ciclos;
	protected int tiempoCiclo;

	public Plant(int x, int y) {
		super(x, y);
	}

	public boolean isSDead() {
		if (this.getHealthPoints() < 1)
			return true;
		else
			return false;
	}

	public String externalise(){
		String symbol = this.icon().substring(0, 1);
		 return ( symbol + "[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:"
				+ this.tiempoCiclo + "], ");
	}
	
	public int getCost() {
		return this.cost;
	}

	public int getHealthPoints() {
		return this.healthPoints;
	}

	public int getCiclos() {
		return this.ciclos;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}

	
	public int getTiempoCiclo() {
		return tiempoCiclo;
	}

	public void setTiempoCiclo(int tiempoCiclo) {
		this.tiempoCiclo = tiempoCiclo;
	}
	
	@Override
	public boolean checkXY() {
		if(this.x<0||this.x>3||this.y<0||this.y>5)
			return false;
		return true;
	}

	public abstract void update(Game game);

	public abstract String toString();

	public abstract String icon();

	public abstract String debugList();

	public abstract Plant parse(String plantName);
	
	@Override
	public abstract boolean checkHealth();
	
	@Override
	public abstract boolean checkTiempoCiclo();

}
