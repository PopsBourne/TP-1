package tp.p3.logic.objects.zombies;

import tp.p3.logic.Game;
import tp.p3.logic.list.ZombieList;
import tp.p3.logic.objects.GameObject;

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
		super(x, y);
	}

	public String getInfo() {
		return null;

	}

	public String externalise() {
		String symbol = this.icon().substring(0, 1);
		return (symbol + "[" + this.healthPoints + ",x:" + this.x + ",y:" + this.y + ",t:" + this.tiempoCiclo + "], ");
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
	

	public void setHealthPoints(int h) {
		this.healthPoints -= h;
	}

	public void loadHealthPoints(int h) {
		this.healthPoints = h;
	}

	public boolean isSDead() {
		if (this.getHealthPoints() < 1)
			return true;
		else
			return false;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAvanza() {
		return avanza;
	}

	public boolean isAlive() {
		return alive;
	}

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

	public int getTiempoCiclo() {
		return tiempoCiclo;
	}

	public void setTiempoCiclo(int tiempoCiclo) {
		this.tiempoCiclo = tiempoCiclo;
	}
	
	@Override
	public boolean checkXY() {
		if(this.x<0||this.x>3||this.y<0||this.y>7)
			return false;
		return true;
	}

	public abstract void update(Game game);

	public abstract String toString();

	public abstract String icon();

	public abstract String debugList();

	public abstract Zombie parse(String zombieName, int x);

	public abstract String getZombieName();

	@Override
	public abstract boolean checkHealth();
	
	@Override
	public abstract boolean checkTiempoCiclo();
}
