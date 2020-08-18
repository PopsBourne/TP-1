package tp.p3.logic.objects.plants;

import tp.p3.logic.Game;

public class Wallnut extends Plant {

	public Wallnut(int x, int y) {
		super(x, y);
		this.harm = 0;
		this.healthPoints = 10;
		this.cost = 50;
		this.ciclos = 0;
	}

	public String toString() {
		String s = "";
		s += "[N]uez: Cost: " + this.cost + " suncoins  Harm: " + this.harm;
		return s;
	}

	public String debugList() {
		String s = "";
		s += "N[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:"
				+ this.tiempoCiclo + "]";
		return s;
	}

	public void update(Game game) {
		this.ciclos++;
	}

	public String icon() {
		return "N[" + this.healthPoints + "]";
	}

	public Plant parse(String plantName) {
		if (plantName.equalsIgnoreCase("nuez")
				|| plantName.equalsIgnoreCase("n"))
			return new Wallnut(0, 0);
		return null;
	}
	
	@Override
	public boolean checkHealth() {
		if (this.healthPoints > 10 || this.healthPoints < 0)
			return false;
		return true;
	}
	
	@Override
	public boolean checkTiempoCiclo() {
		if(this.tiempoCiclo!=0)
			return false;
		return true;
	}
}
