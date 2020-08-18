package tp.p3.logic.objects;

import tp.p3.logic.Game;
import tp.p3.logic.list.GameObjectList;

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

	public abstract boolean isSDead();

	public abstract int getHealthPoints();
	
	public abstract int getTiempoCiclo();

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
	
	public boolean txtAltered() {
		if(!checkXY()||!checkHealth() || !checkTiempoCiclo())
			return true;
		return false;
	}
	
	public abstract boolean checkXY();
	
	
	
	public abstract boolean checkHealth();
	
	public abstract boolean checkTiempoCiclo();

}
