package tp.p1.logic.list;

import tp.p1.logic.Game;
import tp.p1.logic.objects.Peashooter;


public class PeashooterList {
	private Peashooter[]listPeashooter;
	int nPeashooter=0;

	public PeashooterList() {
		listPeashooter = new  Peashooter[32];
	}

	public Peashooter[] getList() {
		return listPeashooter;
	}
	
	public int lengthList() {
		return this.nPeashooter;
	}
	
	public void add(Peashooter psh) {
		listPeashooter[nPeashooter] = psh;
		nPeashooter += 1;
	}
	
	public boolean isSDead(int i) {
		if (listPeashooter[i].getHealthPoints() < 1)
			return true;
		else
			return false;
	}

	public int getnPeashooter() {
		return nPeashooter;
	}

	public void setnPeashooter(int nPeashooter) {
		this.nPeashooter = nPeashooter;
	}

	public void update(Game game) {
		for (int i = 0; i < lengthList(); i++) {
			game.setContCiclos(i);
			listPeashooter[i].update(game);
			if (isSDead(i)) {
				listPeashooter[i] = null;
				fixList(i);
			} 
		}
		
	}
	
	public boolean isPositionEmpty(int x, int y) {
		int posX = x;
		int posY = y;
			for (int i = 0; i < lengthList(); i++) {
				if(listPeashooter[i].getX() == posX && listPeashooter[i].getY() == posY){
					return false;
				}
			}
		return true;
	}
	
	public void fixList(int i){
		for(int j = i; j< lengthList(); j++) {
			listPeashooter[j] = listPeashooter[j+1];
		}
		setnPeashooter(getnPeashooter()-1);
	}

}
