package tp.p3.logic.objects.zombies;

import tp.p3.logic.Game;

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
	
	public Zombie parse(String zombieName, int x) {
		if (zombieName.equalsIgnoreCase("SportyZombie")
				|| zombieName.equalsIgnoreCase("x"))
			return new SportyZombie(x, 7);
		return null;
	}

	@Override
	public String getZombieName() {
		return "SportyZombie";
	}
	
	@Override
	public boolean checkHealth() {
		if (this.healthPoints > 2 || this.healthPoints < 0)
			return false;
		return true;
	}
	
	@Override
	public boolean checkTiempoCiclo() {
		if (this.tiempoCiclo > 1 || this.tiempoCiclo < 0)
			return false;
		return true;
	}
}
