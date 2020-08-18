package tp.p1.logic.list;

import tp.p1.logic.Game;
import tp.p1.logic.objects.Sunflower;

public class SunflowerList {
	private Sunflower[] listSunflower;
	int nSunFlowers = 0;

	public SunflowerList() {
		listSunflower = new Sunflower[32];
	}

	public Sunflower[] getList() {
		return listSunflower;
	}


	public int lengthList() {
		return this.nSunFlowers;
	}
	
	public void add(Sunflower snf) {
		listSunflower[nSunFlowers] = snf;
		nSunFlowers += 1;
	}

	public boolean isSDead(int i) {
		if (listSunflower[i].getHealthPoints() < 1)
			return true;
		else
			return false;
	}

	public int getnSunFlowers() {
		return nSunFlowers;
	}

	public void setnSunFlowers(int nSunFlowers) {
		this.nSunFlowers = nSunFlowers;
	}

	public void update(Game game) {
		for (int i = 0; i < lengthList(); i++) {
			listSunflower[i].update(game);
			if (isSDead(i)) {
				listSunflower[i] = null;
				fixList(i);;
			}
		}

		
	}

	public boolean isPositionEmpty(int x, int y) {
		int posX = x;
		int posY = y;
			for (int i = 0; i < lengthList(); i++) {
				if( listSunflower[i].getX() == posX && listSunflower[i].getY() == posY){
					return false;
				}
			}
		return true;
	}
	
	public void fixList(int i){
		for(int j = i; j< lengthList(); j++) {
			listSunflower[j] = listSunflower[j+1];
		}
		setnSunFlowers(getnSunFlowers()-1);
	
	}

}
