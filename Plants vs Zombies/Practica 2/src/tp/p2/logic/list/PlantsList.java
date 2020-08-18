package tp.p2.logic.list;

import tp.p2.logic.Game;
import tp.p2.logic.objects.GameObject;
import tp.p2.logic.objects.plants.Plant;

public class PlantsList extends GameObjectList {

	public PlantsList() {
		super();
	}

	public GameObject[] getList() {
		return super.getList();
	}

	public void add(Plant pln) {
		super.add(pln);
	}

	// Actualiza la lista de plantas
	public void updateListPlants(Game game) {

		for (int i = 0; i < this.lengthList(); i++) {
			// if (isSDead(i)) {
			// fixList(i);
			// } else
			getList()[i].update(game);
		}
	}

	public void updateDeadPlants() {
		for (int i = 0; i < this.lengthList(); i++) {
			if (isSDead(i))
				fixList(i);
		}
	}

	public boolean isPositionEmpty(int x, int y) {
		for (int i = 0; i < this.lengthList(); i++) {
			if (this.getList()[i].getX() == x && this.getList()[i].getY() == y) {
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
