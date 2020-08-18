package tp.p2.logic.objects.plants;

import tp.p2.logic.Game;

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
		if (this.ciclos > 1) {

			if (game.getZombieList().lengthList() > 0) {
				for (int i = 0; i < game.getZombieList().lengthList(); i++) {
					int zombX = game.getZombieList().getList()[i].getX();
					int zmbY = game.getZombieList().getList()[i].getY();

					if ((zombX == this.x && (zmbY == this.y + 1 || zmbY == this.y - 1))
							|| (zmbY == this.y && (zombX == this.x + 1 || zombX == this.x - 1))) {

						this.boom = true;
						if (!game.getZombieList().getList()[i].isSDead())
							game.getZombieList().getList()[i].setHealthPoints(0);// game.getZombieList().getList()[i].getHealthPoints()
																					// - this.harm
					}
				}

			}
		}
		if (this.boom == true || this.ciclos == 2)
			this.healthPoints = 0;

		this.ciclos++;
		this.tiempoCiclo--;
	}

	public String icon() {
		return "C[" + this.healthPoints + "]";
	}

	// ********************SETTERS Y GETTERS**************************

	public boolean isSDead() {
		if (this.getHealthPoints() < 1)
			return true;
		else
			return false;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCiclos() {
		return ciclos;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}

}
