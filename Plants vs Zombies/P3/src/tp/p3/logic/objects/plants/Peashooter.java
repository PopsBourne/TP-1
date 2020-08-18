package tp.p3.logic.objects.plants;

import tp.p3.logic.Game;

public class Peashooter extends Plant {

	public Peashooter(int x, int y) {
		super(x, y);
		this.harm = 1;
		this.healthPoints = 3;
		this.cost = 50;
		this.ciclos = 0;
		this.tiempoCiclo = 0;
	}

	public String toString() {
		String s = "";
		s += "[P]eashooter: Cost: " + this.cost + " suncoins  Harm: " + this.harm;
		return s;
	}

	public String debugList() {
		String s = "";
		s += "P[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "]";
		return s;
	}

	public void update(Game game) {
		if (this.ciclos >= 1) {
			game.attackPsh(harm, x, y);
		}
		this.ciclos++;
	}

	public String icon() {
		return "P[" + this.healthPoints + "]";
	}

	public Plant parse(String plantName) {
		if (plantName.equalsIgnoreCase("peashooter") || plantName.equalsIgnoreCase("p"))
			return new Peashooter(0, 0);
		return null;
	}

	@Override
	public boolean checkHealth() {
		if (this.healthPoints > 3 || this.healthPoints < 0)
			return false;
		return true;
	}
	
	@Override
	public boolean checkTiempoCiclo() {
		if (this.tiempoCiclo != 0)
			return false;
		return true;
	}
	
}
