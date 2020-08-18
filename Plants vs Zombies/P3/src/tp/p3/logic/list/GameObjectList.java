package tp.p3.logic.list;

import tp.p3.logic.objects.GameObject;

public class GameObjectList {

	private GameObject[] list;
	int nObjects = 0;

	public GameObjectList() {
		list = new GameObject[32];
	}

	public GameObject[] getList() {
		return list;
	}

	public void add(GameObject gameObj) {
		list[nObjects] = gameObj;
		nObjects++;
	}

	public boolean isSDead(int i) {
		if (list[i].isSDead())
			return true;
		return false;
	}

	public int getnObjects() {
		return nObjects;
	}

	public void setnObjects(int obj) {
		this.nObjects = this.nObjects - obj;
	}

	public int lengthList() {
		return this.nObjects;
	}
	
	public void fixList(int i) {
		for (int j = i; j < getnObjects(); j++) {
			this.getList()[j] = this.getList()[j + 1];
		}
		setnObjects(1);
	}

}
