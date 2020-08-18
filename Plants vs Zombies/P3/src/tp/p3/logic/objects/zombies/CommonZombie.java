package tp.p3.logic.objects.zombies;

import tp.p3.logic.Game;

public class CommonZombie extends Zombie {

	public CommonZombie(int x, int y) {
		super(x, y);
		this.harm = 1;
		this.healthPoints = 5;
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

		else if (!checkZombieInPosition(this.x, this.y - 1, game, ciclos)) {// la pos esta ocupada por una planta
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
		return "Z[" + this.healthPoints + "]";
	}

	@Override
	public String toString() {
		String s = "";
		s += "Common [Z]ombie: Speed: " + this.speed + "Harm: " + this.harm + "Life: " + this.healthPoints;
		return s;
	}

	public String debugList() {
		String s = "";
		s += "Z[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "]";
		return s;
	}

	public Zombie parse(String zombieName, int x) {
		if (zombieName.equalsIgnoreCase("CommonZombie") || zombieName.equalsIgnoreCase("z"))
			return new CommonZombie(x, 7);
		return null;
	}

	@Override
	public String getZombieName() {
		return "CommonZombie";
	}
	
	@Override
	public boolean checkHealth() {
		if (this.healthPoints > 5 || this.healthPoints < 0)
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
