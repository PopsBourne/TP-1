package tp.p3.logic.objects.plants;

import tp.p3.logic.Game;

public class Sunflower extends Plant {

	public Sunflower(int x, int y) {
		super(x, y);
		this.harm = 0;
		this.healthPoints = 1;
		this.cost = 20;
		this.ciclos = 0;
	}

	public String toString() {
		String s = "";
		s += "[S]unflower: Cost: " + this.cost + " suncoins  Harm: "
				+ this.harm;
		return s;
	}

	// Se generan 20 soles cada 2 ciclos
	public void update(Game game) {
		if (this.ciclos > 0 && this.ciclos % 2 == 0)
			game.setSunCoins(20);
		this.ciclos++;
	}

	public String debugList() {
		String s = "";
		s += "S[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:"
				+ this.tiempoCiclo + "]";
		return s;
	}

	public String icon() {
		return "S[" + this.healthPoints + "]";
	}

	public Plant parse(String plantName) {
		if (plantName.equalsIgnoreCase("sunflower")
				|| plantName.equalsIgnoreCase("s"))
			return new Sunflower(0, 0);
		return null;
	}
	
	@Override
	public boolean checkHealth() {
		if (this.healthPoints > 1 || this.healthPoints < 0)
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
