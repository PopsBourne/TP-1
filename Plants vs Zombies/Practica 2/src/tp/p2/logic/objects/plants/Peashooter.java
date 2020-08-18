package tp.p2.logic.objects.plants;

import tp.p2.logic.Game;

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

			if (game.getZombieList().lengthList() > 0) {
				for (int i = 0; i < game.getZombieList().lengthList(); i++) {
					if (game.getZombieList().getList()[i].getX() == this.x
							&& game.getZombieList().getList()[i].getY() > this.y
							&& game.getZombieList().getList()[i].getCiclos() >= 1) {
						// shoot
						if (!game.getZombieList().getList()[i].isSDead())
							game.getZombieList().getList()[i]
									.setHealthPoints(game.getZombieList().getList()[i].getHealthPoints() - this.harm);
					}
				}
			}
		}
		this.ciclos++;
	}

	public boolean isSDead() {
		if (this.getHealthPoints() < 1)
			return true;
		else
			return false;
	}

	public String icon() {
		return "P[" + this.healthPoints + "]";
	}

	// ********************SETTERS Y GETTERS**************************

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
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

	@Override
	public int getCost() {
		return this.cost;
	}

}
