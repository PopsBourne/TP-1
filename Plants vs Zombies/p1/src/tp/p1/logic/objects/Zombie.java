package tp.p1.logic.objects;

import tp.p1.logic.Game;

public class Zombie {

	private int x, y;
	private int harm;
	private int healthPoints;
	private int speed;
	private Game game;
	private int ciclos;
	private boolean avanza;
	private boolean alive;

	public Zombie(int x, int y) {
		this.x = x;
		this.y = y;
		this.harm = 1;
		this.healthPoints = 5;
		this.speed = 1;
		this.ciclos = 0;
		this.alive = true;
	}

	public void update(Game game) {

		int x = this.x;
		int y = this.y;

		
		if (game.isPositionEmpty(x, y - 1) && this.ciclos == 2) {
			this.avanza = true;
			this.ciclos = 1;
		}
		
		//Revisar
		else if (!game.isPositionEmpty(x, y - 1) && game.getZombieList().isPositionEmpty(x, y - 1)
				&& this.ciclos >= 1) {// Nagini ataca!
			this.avanza = false;
			//REALIZAR METODO AUXILIAR PARA ATACAR=?
			if (game.getSFInPosition(x, y - 1) != null && game.getSFInPosition(x, y - 1).getCiclos() > 1) {
				game.getSFInPosition(x, y - 1)
						.setHealthPoints(game.getSFInPosition(x, y - 1).getHealthPoints() - this.harm);

			} else if (game.getPSInPosition(x, y - 1) != null && game.getPSInPosition(x, y - 1).getCiclos() > 1) {
				game.getPSInPosition(x, y - 1)
						.setHealthPoints(game.getPSInPosition(x, y - 1).getHealthPoints() - this.harm);
			}
			if (this.ciclos < 2)
				this.ciclos++;
		} else {
			if (this.ciclos < 2)
				this.ciclos++;
			this.avanza = false;
		}
	}

	public String icon() {
		return "Z[" + this.healthPoints + "]";
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
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

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void harm(){
		game.dealDamage();
	}

	public int getCiclos() {
		return ciclos;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}
	
}
