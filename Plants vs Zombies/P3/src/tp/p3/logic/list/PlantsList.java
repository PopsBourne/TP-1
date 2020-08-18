package tp.p3.logic.list;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.p3.exceptions.AlteredFileException;
import tp.p3.logic.Game;
import tp.p3.logic.PlantFactory;
import tp.p3.logic.objects.plants.Plant;

public class PlantsList extends GameObjectList {

	public PlantsList() {
		super();
	}

	public Plant getList(int i) {
		return (Plant) super.getList()[i];
	}

	public void add(Plant pln) {
		super.add(pln);
	}

	public void updateListPlants(Game game) {
		for (int i = 0; i < this.lengthList(); i++) {
			getList(i).update(game);
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
			if (this.getX(i) == x && this.getY(i) == y) {
				return false;
			}
		}
		return true;
	}

	public Plant getPosition(int x, int y) {
		Plant pln = null;

		for (int i = 0; i < this.lengthList(); i++)
			if (this.getX(i) == x && this.getY(i) == y)
				pln = this.getList(i);
		return pln;
	}

	public void store(BufferedWriter out) throws IOException {
		for (int i = 0; i < this.lengthList(); i++)
			out.write(getList(i).externalise());
		out.newLine();
	}

	public void loadPlant(String[] line) throws AlteredFileException {
		int i = 0;
		while (i < line.length) {
			Plant pln = PlantFactory.getPlant(String.valueOf(line[i].charAt(0)));
			pln.setX(Character.getNumericValue(line[i + 1].charAt(2)));
			pln.setY(Character.getNumericValue(line[i + 2].charAt(2)));
			pln.setHealthPoints(Character.getNumericValue(line[i].charAt(2)));
			pln.setTiempoCiclo(Character.getNumericValue(line[i + 3].charAt(2)));
			
			if(pln.txtAltered())
				throw new AlteredFileException("File altered! Load fail.");
			
			this.add(pln);
			i += 4;
		}
	}

	public int getX(int i) {
		return this.getList(i).getX();
	}

	public int getY(int i) {
		return this.getList(i).getY();
	}

	@Override
	public int lengthList() {
		return super.lengthList();
	}
}
