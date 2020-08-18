package tp.p2.logic.objects.zombies;

import tp.p2.logic.Game;

public class SportyZombie extends Zombie {

	public SportyZombie(int x, int y) {
		super(x, y);

		this.harm = 1;
		this.healthPoints = 2;
		this.speed = 1;
		this.ciclos = 0;
		this.alive = true;
		this.tiempoCiclo = 1;
	}

	public void update(Game game) {

		if (!game.isFull(this.x, this.y - 1) && this.ciclos == 1) {
			this.avanza = true;
			this.ciclos = 0;
			this.tiempoCiclo = 1;
		}

		else if (!checkZombieInPosition(this.x, this.y - 1, game, ciclos)) {
			attack(game);
			if (this.ciclos < 1) {
				this.ciclos++;
				this.tiempoCiclo--;
			}
		} else {
			if (this.ciclos < 1) {
				this.ciclos++;
				this.tiempoCiclo--;
			}
			this.avanza = false;
		}

		if (this.isAvanza())
			this.y--;
	}

	public String icon() {
		return "X[" + this.healthPoints + "]";
	}

	@Override
	public String toString() {
		String s = "";
		s += "Sporty [Z]ombie: Speed: " + this.speed + "Harm: " + this.harm + "Life: " + this.healthPoints;
		return s;
	}

	public String debugList() {
		String s = "";
		s += "X[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "]";
		return s;
	}

	public boolean isSDead() {
		if (this.getHealthPoints() < 1)
			return true;
		else
			return false;

	}
	// ******************GETTERS Y SETTERS******************

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

	public boolean isAvanza() {
		return avanza;
	}

	@Override
	public void setAvanza(boolean avanza) {
		this.avanza = avanza;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public int getCiclos() {
		return this.ciclos;
	}

}
