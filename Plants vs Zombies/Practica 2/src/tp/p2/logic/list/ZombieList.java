package tp.p2.logic.list;

import tp.p2.logic.Game;
import tp.p2.logic.objects.GameObject;
import tp.p2.logic.objects.zombies.Zombie;

public class ZombieList extends GameObjectList {

	public ZombieList() {
		super();
	}

	public GameObject[] getList() {
		return super.getList();
	}

	public void add(Zombie zmb) {
		super.add(zmb);
	}

	public void updateListZombies(Game game) {

		for (int j = 0; j < this.lengthList(); j++) {
			if (super.isSDead(j)) {
				fixList(j);
			} else
				getList()[j].update(game);
		}
	}

	public boolean isPositionEmpty(int x, int y) {
		int posX = x;
		int posY = y;
		for (int i = 0; i < this.lengthList(); i++) {
			if (this.getList()[i].getX() == posX && this.getList()[i].getY() == posY) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int lengthList() {
		return super.lengthList();
	}

}
