package tp.p2.logic;

import java.util.Random;

import tp.p2.logic.list.PlantsList;
import tp.p2.logic.list.ZombieList;
import tp.p2.logic.objects.plants.Plant;
import tp.p2.logic.objects.zombies.Zombie;
import tp.p2.util.MyStringUtils;

public class Game {

	private Random rand;
	private int contCiclos;

	private ZombieList zombieList;
	private PlantsList plantsList;

	private SuncoinManager sunCoins;
	private ZombieManager zombiesLeft;

	private Level level;

	private boolean exit;
	private boolean fin;
	private boolean loseGame;
	private int numZ;

	public Game(Random rand, int numZ, double frq) {
		this.rand = rand;
		this.contCiclos = 0;
		this.sunCoins = new SuncoinManager();
		this.zombiesLeft = new ZombieManager(numZ, frq, rand);

		this.zombieList = new ZombieList();
		this.plantsList = new PlantsList();

		this.numZ = numZ;
		this.loseGame = false;
		this.exit = false;
	}

	// ADD
	// ************************************************************************************

	public boolean isPositionEmpty(int x, int y) {
		return plantsList.isPositionEmpty(x, y) && zombieList.isPositionEmpty(x, y);
	}

	public void addZombieToGame(Zombie zmb) {
		if (isPositionEmpty(zmb.getX(), zmb.getY()) && zombiesLeft.randomZombies()) {
			zombieList.add(zmb);
		//	update();
			this.zombiesLeft.setRemainingZ(getZombiesLeft().getRemainingZ() - 1);
		}
	}

	public void addPlantToGame(Plant plant, int x, int y) {
		plant.setX(x);
		plant.setY(y);
		this.plantsList.add(plant);
	//	update();
	}

	// ************************************************************************************

	
	// UPDATE
	public void update() {

		this.plantsList.updateListPlants(this);
		this.zombieList.updateListZombies(this);
		this.plantsList.updateDeadPlants();
		this.setContCiclos(this.contCiclos + 1);

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

	// Comprueba si la siguiente posicion del zombie est� vacia
	public boolean isFull(int i, int j) {
		if (getPLInPosition(i, j) == null && getZInPosition(i, j) == null)
			return false;
		else
			return true;
	}

	public Plant getPLInPosition(int x, int y) {
		Plant pln = null;

		for (int i = 0; i < this.plantsList.getnObjects(); i++)
			if (this.plantsList.getList()[i].getX() == x && this.plantsList.getList()[i].getY() == y)
				pln = (Plant) this.plantsList.getList()[i];
		return pln;
	}

	public Zombie getZInPosition(int x, int y) {
		Zombie zmb = null;
		for (int i = 0; i < this.zombieList.getnObjects(); i++)
			if (this.zombieList.getList()[i].getX() == x && this.zombieList.getList()[i].getY() == y)
				zmb = (Zombie) this.zombieList.getList()[i];
		return zmb;
	}


	// EXECUTE DE LOS COMMANDS

	// EXIT
	public void exit() {
		this.setExit(false);
		System.out.println(MyStringUtils.centre("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>", 100));
		System.exit(0);
	}

	// RESET
	public void reset() {
		this.zombieList = new ZombieList();
		this.plantsList = new PlantsList();

		this.contCiclos = 0;
		this.sunCoins.setCoins(50);
		this.zombiesLeft.setRemainingZ(this.numZ);
		// this.board = new String[4][8];// ??????
	}

	// **************************************************************************************************************


	// Casilla aleatoria de salida del zombie
	// public int getRandomX() {
	// for (int i = 0; i < 4; i++) {
	// randomX = rand.nextInt(4);
	// if (getBoard()[randomX][7] == " ")
	// i = 4;
	// }
	// return randomX;
	// }
	//
	// public void setRandomX(int randomX) {
	// this.randomX = randomX;
	// }
	// ***************************************************

	public String checkPosPln(int i, int j) {

		if (getPLInPosition(i, j) == null)
			return " ";
		else
			return getPLInPosition(i, j).debugList();
	}

	public String checkPosZmb(int i, int j) {

		if (getZInPosition(i, j) == null)
			return " ";
		else
			return getZInPosition(i, j).debugList();
	}

	public String checkPosLists(int i, int j) {
		if (getPLInPosition(i, j) == null && getZInPosition(i, j) == null)
			return " ";
		else if (getPLInPosition(i, j) != null)
			return getPLInPosition(i, j).icon();
		else
			return getZInPosition(i, j).icon();
	}

	public int getTamListPln() {
		return this.plantsList.getnObjects();
	}

	public int getTamListZmb() {
		return this.zombieList.getnObjects();
	}


	// ********************SETTERS Y GETTERS**************************

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isLoseGame() {
		return loseGame;
	}

	public ZombieList getZombieList() {
		return zombieList;
	}

	public void setZombieList(ZombieList zombieList) {
		this.zombieList = zombieList;
	}

	public PlantsList getPlantsList() {
		return plantsList;
	}

	public void setPlantsList(PlantsList plantsList) {
		this.plantsList = plantsList;
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

	public ZombieManager getZombiesLeft() {
		return zombiesLeft;
	}

	public void setZombiesLeft(ZombieManager zombiesLeft) {
		this.zombiesLeft = zombiesLeft;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
