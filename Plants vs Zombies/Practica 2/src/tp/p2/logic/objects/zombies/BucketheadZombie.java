package tp.p2.logic.objects.zombies;

import tp.p2.logic.Game;

public class BucketheadZombie extends Zombie {

	public BucketheadZombie(int x, int y) {
		super(x, y);
		this.harm = 1;
		this.healthPoints = 8;
		this.speed = 1;
		this.ciclos = 0;
		this.alive = true;
		this.tiempoCiclo = 4;
	}

	public void update(Game game) {

		if (!game.isFull(this.x, this.y - 1) && this.ciclos == 4) {
			this.avanza = true;
			this.ciclos = 1;
			this.tiempoCiclo = 4;
		}

		else if (!checkZombieInPosition(this.x, this.y - 1, game, ciclos)) {
			attack(game);
			if (this.ciclos < 4) {
				this.ciclos++;
				this.tiempoCiclo--;
			}
		} else {
			if (this.ciclos < 4) {
				this.ciclos++;
				this.tiempoCiclo--;
			}
			this.avanza = false;
		}

		if (this.isAvanza())
			this.y--;
	}

	public String icon() {
		return "W[" + this.healthPoints + "]";
	}

	@Override
	public String toString() {
		String s = "";
		s += "Buckethead [Z]ombie: Speed: " + this.speed + "Harm: " + this.harm + "Life: " + this.healthPoints;
		return s;
	}

	public String debugList() {
		String s = "";
		s += "Z[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "]";
		return s;
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

	public boolean isAlive() {
		return alive;
	}

	@Override
	public void setAvanza(boolean avanza) {
		this.avanza = avanza;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getCiclos() {
		return ciclos;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}

}
