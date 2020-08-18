package tp.p1.logic;

import java.util.Random;
import tp.p1.logic.list.PeashooterList;
import tp.p1.logic.list.SunflowerList;
import tp.p1.logic.list.ZombieList;
import tp.p1.logic.objects.*;

public class Game {

	private Random rand;
	private int contCiclos;

	private SunflowerList sunflowerList;
	private PeashooterList peashooterList;
	private ZombieList zombieList;

	private GamePrinter gamePrinter;
	private SuncoinManager sunCoins;
	private ZombieManager zombiesLeft;

	private boolean fin;
	private boolean loseGame;
	private String[][] board;
	private int randomX;
	private int numZ;
	
	private int contList;

	public Game(Random rand, int numZ, double frq) {
		this.rand = rand;
		this.contCiclos = 0;
		this.sunCoins = new SuncoinManager();
		this.zombiesLeft = new ZombieManager(numZ, frq, rand);

		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();
		this.numZ = numZ;
		this.gamePrinter = new GamePrinter(this, 4, 8);
		this.loseGame = false;

	}

	// ADD
	// ******************************************

	public boolean isPositionEmpty(int x, int y) {
		return sunflowerList.isPositionEmpty(x, y) && peashooterList.isPositionEmpty(x, y)
				&& zombieList.isPositionEmpty(x, y);
	}

	public void addSunflower(Sunflower snf) {
		if (getSunCoins() >= 20 && isPositionEmpty(snf.getX(), snf.getY())) {
			sunflowerList.add(snf);

			this.gamePrinter.setBoard(snf.getX(), snf.getY(), snf.icon());
			setSunCoins(getSunCoins() - 20);
		}
	}

	public void addPeashooter(Peashooter psh) {
		if (getSunCoins() >= 50 && isPositionEmpty(psh.getX(), psh.getY())) {
			peashooterList.add(psh);

			this.gamePrinter.setBoard(psh.getX(), psh.getY(), psh.icon());
			setSunCoins(getSunCoins() - 50);
		}
	}

	public void addZombie(Zombie zmb) {
		if (isPositionEmpty(zmb.getX(), zmb.getY())) {
			zombieList.add(zmb);

			this.gamePrinter.setBoard(zmb.getX(), zmb.getY(), zmb.icon());
			this.zombiesLeft.setRemainingZ(getZombiesLeft().getRemainingZ() - 1);
		}
	}
	// ******************************************

	// UPDATE
	public void update() {
		sunflowerList.update(this);
		peashooterList.update(this);
		zombieList.update(this);

		// Comprobar si has perdido
		if (lose())
			this.setFin(true);

		fin();
	}

	// DRAW
	public String draw() {
		String s = "";
		// s += "Random seed used: " + this.rand + "\n";// CAMBIAR A SEED
		s += "Number of cycles: " + getContCiclos() + "\n";
		s += "Sun coins: " + this.sunCoins.getCoins() + "\n";
		s += "Remaining zombies: " + getZombiesLeft().getRemainingZ() + "\n";
		return s;
	}

	// RESET
	public void reset() {
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList();

		this.contCiclos = 0;
		this.sunCoins.setCoins(50);
		this.zombiesLeft.setRemainingZ(this.numZ);
		this.gamePrinter = new GamePrinter(this, 4, 8);
	}

	// FIN DEL JUEGO
	private boolean fin() {
		if (this.getZombiesLeft().getRemainingZ() == 0 && this.getZombieList().getList()[0] == null) {
			fin = true;
		} else if (this.lose()) {
			fin = true;
		} else
			fin = false;
		return fin;
	}

	private boolean lose() {
		for (int x = 0; x < 4; x++)
			if (this.getZInPosition(x, 0) != null)
				this.loseGame = true;
		return this.loseGame;
	}

	// **************************************************************************************************************

	// Comprueba si el tipo se encuentra en la posicion dada y devuelve el tipo o
	// null si no hay nada
	// *************************************************************
	public Sunflower getSFInPosition(int x, int y) {
		Sunflower snf = null;
		for (int i = 0; i < this.sunflowerList.lengthList(); i++)
			if (this.sunflowerList.getList()[i].getX() == x && this.sunflowerList.getList()[i].getY() == y)
				snf = this.sunflowerList.getList()[i];
		return snf;
	}

	public Peashooter getPSInPosition(int x, int y) {
		Peashooter psh = null;
		for (int i = 0; i < this.peashooterList.lengthList(); i++)
			if (this.peashooterList.getList()[i].getX() == x && this.peashooterList.getList()[i].getY() == y)
				psh = this.peashooterList.getList()[i];
		return psh;
	}

	public Zombie getZInPosition(int x, int y) {
		Zombie zmb = null;
		for (int i = 0; i < this.zombieList.lengthList(); i++)
			if (this.zombieList.getList()[i].getX() == x && this.zombieList.getList()[i].getY() == y)
				zmb = this.zombieList.getList()[i];
		return zmb;
	}
	// *************************************************************

	// Metodo auxiliar para realizar el List
	// ***************
	public String showPlants() {
		Sunflower snf = new Sunflower(0, 0);
		Peashooter psh = new Peashooter(0, 0);
		String s = "";
		s += snf.toString() + "\n" + psh.toString();
		return s;
	}

	// **************************************************************************************************************

	// GETTERS Y SETTERS

	// Casilla aleatoria de salida del zombie
	public int getRandomX() {
		for (int i = 0; i < 4; i++) {
			randomX = rand.nextInt(4);
			if (this.getGamePrinter().getBoard()[randomX][7] == " ")
				i = 4;
		}
		return randomX;
	}

	public void setRandomX(int randomX) {
		this.randomX = randomX;
	}

	public GamePrinter getGamePrinter() {
		return gamePrinter;
	}

	public void setGamePrinter(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;
	}

	public boolean isLoseGame() {
		return loseGame;
	}

	public SunflowerList getSunflowerList() {
		return sunflowerList;
	}

	public void setSunflowerList(SunflowerList sunflowerList) {
		this.sunflowerList = sunflowerList;
	}

	public PeashooterList getPeashooterList() {
		return peashooterList;
	}

	public void setPeashooterList(PeashooterList peashooterList) {
		this.peashooterList = peashooterList;
	}

	public ZombieList getZombieList() {
		return zombieList;
	}

	public void setZombieList(ZombieList zombieList) {
		this.zombieList = zombieList;
	}

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public Random get_rand() {
		return rand;
	}

	public void set_rand(Random rand) {
		this.rand = rand;
	}

	public int getContCiclos() {
		return contCiclos;
	}

	public void setContCiclos(int cont) {
		this.contCiclos = cont;
	}

	public int getSunCoins() {
		return this.sunCoins.getCoins();
	}

	public void setSunCoins(int sunCoins) {
		this.sunCoins.setCoins(sunCoins);
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	public ZombieManager getZombiesLeft() {
		return zombiesLeft;
	}

	public void setZombiesLeft(ZombieManager zombiesLeft) {
		this.zombiesLeft = zombiesLeft;
	}

	public void addSunCoins(int i) {
		this.sunCoins.setCoins(this.getSunCoins() + i);
	}
	

	public int getContList() {
		return contList;
	}

	public void setContList(int contList) {
		this.contList = contList;
	}

	public void dealDamage() {
		// TODO Auto-generated method stub

	}

	public void peashooterShoots(int harm, int contList) {
		// boolean firstReached = false;
		if (zombieList.getnZombies() > 0) {
			for (int i = 0; i <= contList; i++) {
				for (int j = 0; j < getZombieList().lengthList(); j++) {
					if (getPeashooterList().getList()[i].getX() == getZombieList().getList()[j].getX()
							&& getPeashooterList().getList()[i].getY() < getZombieList().getList()[j].getY() && getZombieList().getList()[j].getCiclos() > 0) {

						if (getPeashooterList().getList()[i].getCiclos() > 0) {
							getZombieList().getList()[j].setHealthPoints(getZombieList().getList()[j].getHealthPoints()
									- getPeashooterList().getList()[i].getHarm());
							// firstReached = true;
						}
					}
				}
			}
		}
	}

}
