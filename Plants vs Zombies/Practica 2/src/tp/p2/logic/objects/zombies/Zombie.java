package tp.p2.logic.objects.zombies;

import tp.p2.logic.Game;
import tp.p2.logic.list.PlantsList;
import tp.p2.logic.list.ZombieList;
import tp.p2.logic.objects.GameObject;

public abstract class Zombie extends GameObject {

	protected String zombieName;
	protected String firstLetter;
	protected int healthPoints;
	protected int harm;
	protected int speed;
	protected int ciclos;
	protected boolean avanza;
	protected boolean alive;
	protected int tiempoCiclo;

	public Zombie(int x, int y) {
		super(x, 7);
	}

	public String getInfo() {
		return null;

	}

	public boolean checkZombieInPosition(int x, int y, Game game, int ciclos) {
		if (game.isFull(x, y) && !game.checkPosZmb(x, y).contains("Z") && !game.checkPosZmb(x, y).contains("W")
				&& !game.checkPosZmb(x, y).contains("X") && ciclos >= 1)
			return false;
		else
			return true;
	}

	public void attack(Game game) {
		this.setAvanza(false);

		// game.getPLInPosition(x, y - 1) != null &&
		if (game.getPLInPosition(x, y - 1).getCiclos() >= 1 && !game.getPLInPosition(x, y - 1).isSDead()) {
			game.getPLInPosition(x, y - 1)
					.setHealthPoints(game.getPLInPosition(x, y - 1).getHealthPoints() - this.harm);
		}
	}

	public boolean isAvanza(ZombieList zmbL) {
		if (y - 1 >= 0 && this.getCiclos() % 2 == 0 && this.getCiclos() > 1)
			this.setAvanza(true);
		else
			this.setAvanza(false);
		return this.isAvanza();
	}

	public abstract void update(Game game);

	public abstract String toString();

	public abstract String icon();

	public abstract String debugList();

	public abstract int getX();

	public abstract int getY();

	public abstract int getCiclos();

	public abstract int getHealthPoints();

	public abstract void setHealthPoints(int hp);

	public abstract boolean isAvanza();

	public abstract void setAvanza(boolean alive);

	public abstract void setPosition(int x, int y);
}
