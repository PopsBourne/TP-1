package tp.p3.logic.list;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.p3.exceptions.AlteredFileException;
import tp.p3.logic.Game;
import tp.p3.logic.ZombieFactory;
import tp.p3.logic.objects.zombies.Zombie;

public class ZombieList extends GameObjectList {

	public ZombieList() {
		super();
	}

	public Zombie getList(int j) {
		return (Zombie) super.getList()[j];
	}

	public void add(Zombie zmb) {
		super.add(zmb);
	}

	public void updateListZombies(Game game) {

		for (int j = 0; j < this.lengthList(); j++) {
			if (super.isSDead(j)) {
				fixList(j);
			} else
				getList(j).update(game);
		}
	}

	public boolean isPositionEmpty(int x, int y) {
		for (int i = 0; i < this.lengthList(); i++) {
			if (getX(i) == x && getY(i) == y) {
				return false;
			}
		}
		return true;
	}

	public Zombie getZPosition(int x, int y) {
		Zombie zmb = null;
		for (int j = 0; j < this.lengthList(); j++)
			if (getX(j) == x && getY(j) == y)
				zmb = this.getList(j);
		return zmb;
	}

	public void store(BufferedWriter out) throws IOException {
		for (int i = 0; i < this.lengthList(); i++)
			out.write(getList(i).externalise());
		out.newLine();
	}

	public void loadZombie(String[] line) throws AlteredFileException {
		int i = 0;
		while (i < line.length) {
			Zombie zmb = ZombieFactory.getSavedZombies((String.valueOf(line[i].charAt(0))));
			zmb.setX(Character.getNumericValue(line[i + 1].charAt(2)));
			zmb.setY(Character.getNumericValue(line[i + 2].charAt(2)));
			zmb.loadHealthPoints(Character.getNumericValue(line[i].charAt(2)));
			zmb.setTiempoCiclo(Character.getNumericValue(line[i + 3].charAt(2)));
			
			if(zmb.txtAltered())
				throw new AlteredFileException("File altered! Load fail.");
				
			this.add(zmb);
			i += 4;
		}
	}

	@Override
	public int lengthList() {
		return super.lengthList();
	}

	public int getX(int j) {
		return this.getList(j).getX();
	}

	public int getY(int j) {
		return this.getList(j).getY();
	}

	public boolean isDead(int j) {
		return isSDead(j);
	}

	public void setHealthPoints(int j, int harm) {
		getList(j).setHealthPoints(harm);
	}

	public int getCiclos(int j) {
		return getList(j).getCiclos();
	}
}
