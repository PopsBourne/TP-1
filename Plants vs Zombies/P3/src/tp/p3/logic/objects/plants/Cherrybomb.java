package tp.p3.logic.objects.plants;

import tp.p3.logic.Game;

public class Cherrybomb extends Plant {

	private boolean boom;

	public Cherrybomb(int x, int y) {
		super(x, y);
		this.harm = 10;
		this.healthPoints = 2;
		this.cost = 50;
		this.ciclos = 0;
		this.tiempoCiclo = 2;
		this.boom = false;
	}

	public String toString() {
		String s = "";
		s += "Peta[c]ereza: Cost: " + this.cost + " suncoins  Harm: " + this.harm;
		return s;
	}

	public String debugList() {
		String s = "";
		s += "C[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "]";
		return s;
	}

	public void update(Game game) {
		if (this.ciclos > 1)
			game.attackChe(this.harm, x, y, false);

		if (this.boom == true || this.ciclos == 2)
			this.healthPoints = 0;

		this.ciclos++;
		this.tiempoCiclo--;
	}

	public String icon() {
		return "C[" + this.healthPoints + "]";
	}

	public Plant parse(String plantName) {
		if (plantName.equalsIgnoreCase("petacereza") || plantName.equalsIgnoreCase("c"))
			return new Cherrybomb(0, 0);
		return null;
	}

	@Override
	public boolean checkHealth() {
		if (this.healthPoints > 2 || this.healthPoints < 0)
			return false;
		return true;
	}

	@Override
	public boolean checkTiempoCiclo() {
		if (this.tiempoCiclo > 2 || this.tiempoCiclo < 0)
			return false;
		return true;
	}
}
