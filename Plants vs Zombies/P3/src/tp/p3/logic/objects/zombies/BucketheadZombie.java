package tp.p3.logic.objects.zombies;

import tp.p3.logic.Game;


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
	
	public Zombie parse(String zombieName, int x) {
		if (zombieName.equalsIgnoreCase("BucketheadZombie")
				|| zombieName.equalsIgnoreCase("w"))
			return new BucketheadZombie(x, 7);
		return null;
	}

	@Override
	public String getZombieName() {
		return "BucketheadZombie";
	}
	@Override
	public boolean checkHealth() {
		if (this.healthPoints > 8 || this.healthPoints < 0)
			return false;
		return true;
	}
	@Override
	public boolean checkTiempoCiclo() {
		if (this.tiempoCiclo > 4 || this.tiempoCiclo < 0)
			return false;
		return true;
	}
}
