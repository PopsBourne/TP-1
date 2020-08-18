package tp.p2.logic.objects.plants;

import tp.p2.logic.Game;
import tp.p2.logic.objects.GameObject;

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

	public abstract int getCost();

	public  String getInfo() {
		return null;
	}

	public abstract int getHealthPoints();
 
	public abstract void setHealthPoints(int healthPoints);

	public abstract int getCiclos();

	public abstract void update(Game game);

	public abstract String toString();

	public abstract boolean isSDead();

	public abstract int getX();

	public abstract int getY();

	public abstract void setPosition(int x, int y);

	public abstract String icon();

	public abstract String debugList();
}
