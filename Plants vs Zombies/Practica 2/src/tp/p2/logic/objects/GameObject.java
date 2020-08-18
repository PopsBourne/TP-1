package tp.p2.logic.objects;

import tp.p2.logic.Game;
import tp.p2.logic.list.GameObjectList;

public abstract class GameObject {

	protected int x;
	protected int y;
	private GameObjectList gameObjList;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.gameObjList = new GameObjectList();
	}

	public abstract void update(Game game);

	public abstract String getInfo();

	public abstract boolean isSDead();

	public abstract int getHealthPoints();

	public abstract void setHealthPoints(int i);

	public abstract int getX();

	public abstract int getY();

	public abstract int getCiclos();

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
