package tp.p1.logic.list;
import tp.p1.logic.Game;
import tp.p1.logic.objects.Zombie;

public class ZombieList {
	private Zombie[]listZombie;
	int nZombies=0;

	public ZombieList() {
		listZombie = new  Zombie[32];
	}
	
	public Zombie[] getList() {
		return listZombie;
	}

	
	public int lengthList() {
		return this.nZombies;
	}
	
	public void add( Zombie zmb ) {
		listZombie[nZombies] = zmb;
		nZombies += 1;
	}
	
	public boolean isSDead(int i) {
		if (listZombie[i].getHealthPoints() <= 0)
			return true;
		else
			return false;
	}

	public int getnZombies() {
		return nZombies;
	}

	public void setnZombies(int nZombies) {
		this.nZombies = nZombies;
	}

	public void update(Game game) {
		for (int i = 0; i < lengthList(); i++) {
			listZombie[i].update(game);
			int x = listZombie[i].getX();
			int y = listZombie[i].getY();

			if (isSDead(i)) {
				getList()[i] = null;
				game.getGamePrinter().setBoard(x, y, " ");// Elimina el zombie abatido
				fixList(i);
			}
			else {

			if (listZombie[i].isAvanza() ) {

				listZombie[i].setY(y - 1);
				//Si borro las dos sigueinte lineas de codigo peta
				game.getGamePrinter().setBoard(x, y, " ");
				game.getGamePrinter().setBoard(getList()[i].getX(), getList()[i].getY(), getList()[i].icon());
				//mismo comentario para el else if
			} else if (!isSDead(i))// PRUEBA
				game.getGamePrinter().setBoard(getList()[i].getX(), getList()[i].getY(), getList()[i].icon());
			}

		}
		
	}
	
	public boolean isPositionEmpty(int x, int y) {
		int posX = x;
		int posY = y;
			for (int i = 0; i < lengthList(); i++) {
				if(listZombie[i].getX() == posX && listZombie[i].getY() == posY){
					return false;
				}
				
			}
		return true;
	}
	
	private void fixList(int i) {
		for(int j = i; j< lengthList(); j++) {
			listZombie[j] = listZombie[j+1];
		}
		setnZombies(getnZombies()-1);
	}
	
}
