package tp.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;

import tp.p3.exceptions.AlteredFileException;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.board.ReleasePrinter;
import tp.p3.logic.list.PlantsList;
import tp.p3.logic.list.ZombieList;
import tp.p3.logic.objects.plants.Plant;
import tp.p3.logic.objects.zombies.Zombie;
import tp.p3.util.MyStringUtils;

public class Game {

	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";

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

	public Game(Random rand, Level level) {
		this.level = level;
		this.rand = rand;
		this.contCiclos = 0;
		this.numZ = level.getNumberOfZombies();
		this.sunCoins = new SuncoinManager();
		this.zombiesLeft = new ZombieManager(this.numZ, level.getZombieFrequency(), rand);

		this.zombieList = new ZombieList();
		this.plantsList = new PlantsList();

		this.loseGame = false;
		this.exit = false;
	}

	// ADD
	// ************************************************************************************

	public boolean isPositionEmpty(int x, int y) {
		return plantsList.isPositionEmpty(x, y) && zombieList.isPositionEmpty(x, y);
	}

	public void addZombieToGame(Zombie zmb) {
		if (isPositionEmpty(zmb.getX(), zmb.getY()) && zombiesLeft.randomZombies()
				&& this.zombiesLeft.getRemainingZ() > 0) {
			zombieList.add(zmb);
			this.zombiesLeft.setRemainingZ(1);
		}
	}

	public void addPlantToGame(Plant plant, int x, int y) {
		plant.setX(x);
		plant.setY(y);
		this.plantsList.add(plant);
	}

	// ************************************************************************************

	// UPDATE
	public void update() {

		this.plantsList.updateListPlants(this);
		this.zombieList.updateListZombies(this);
		this.plantsList.updateDeadPlants();
		this.setContCiclos(1);

		if (lose())
			this.setFin(true);
		fin();
	}

	// DRAW
	public String draw() {
		String s = "";
		s += "Number of cycles: " + getContCiclos() + "\n";
		s += "Sun coins: " + this.sunCoins.getCoins() + "\n";
		s += "Remaining zombies: " + getZombiesLeft() + "\n";
		return s;
	}

	// FIN DEL JUEGO
	private boolean fin() {
		if (this.zombiesLeft.getRemainingZ() == 0 && this.zombieList.getList(0) == null) {
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

	// ATTACK PEASHOOTER

	public void attackPsh(int harm, int x, int y) {
		if (this.zombieList.lengthList() > 0) {
			for (int i = 0; i < this.zombieList.lengthList(); i++) {
				if (this.zombieList.getX(i) == x && this.zombieList.getY(i) > y && this.zombieList.getCiclos(i) >= 1) {
					if (!this.zombieList.isSDead(i))
						this.zombieList.setHealthPoints(i, harm);
				}
			}
		}
	}

	// ATTACK CHERRYBOMB

	public void attackChe(int harm, int x, int y, boolean boom) {

		if (this.zombieList.lengthList() > 0) {
			for (int i = 0; i < this.zombieList.lengthList(); i++) {
				int zombX = this.zombieList.getX(i);
				int zmbY = this.zombieList.getY(i);

				if ((zombX == x && (zmbY == y + 1 || zmbY == y - 1))
						|| (zmbY == y && (zombX == x + 1 || zombX == x - 1))) {

					boom = true;
					if (!this.zombieList.isSDead(i))
						this.zombieList.setHealthPoints(i, harm);
				}
			}

		}
	}

	// EXECUTE DE LOS COMMANDS

	// EXIT
	public boolean exit() {
		this.setExit(false);
		System.out.println(MyStringUtils.centre("<<<<<<< G  A  M  E       O  V  E  R >>>>>>>", 100));
		System.exit(0);
		return exit;
	}

	// RESET
	public boolean reset() {
		this.zombieList = new ZombieList();
		this.plantsList = new PlantsList();

		this.contCiclos = 0;
		this.sunCoins.resetSunCoins(50);
		this.zombiesLeft.resetRemainingZ(this.numZ);

		return true;
	}

	// **************************************************************************************************************

	public void store(BufferedWriter out) throws IOException {
		out.write("cycle: " + this.contCiclos + "\r\n" + "suncoins: " + this.getSunCoins() + "\r\n" + "level: "
				+ this.level + "\r\n" + "remZombies: " + this.getZombiesLeft());
		out.newLine();
		try {
			out.write("plantList: ");
			this.plantsList.store(out);
			out.write("zombieList: ");
			this.zombieList.store(out);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Game load(BufferedReader out) throws CloneNotSupportedException, CommandExecuteException, IOException, AlteredFileException {
		Game oldGame = cln();
		try {
			String[] line;
			line = loadLine(out, "cycle", false);
			this.contCiclos = Integer.valueOf(line[0]);
			line = loadLine(out, "suncoins", false);
			this.sunCoins.setCoins(Integer.valueOf(line[0]));
			line = loadLine(out, "level", false);
			this.level = Level.valueOf(line[0]);
			line = loadLine(out, "remZombies", false);
			this.zombiesLeft.resetRemainingZ(Integer.valueOf(line[0]));

			line = loadLine(out, "plantList", true);
			this.plantsList = new PlantsList();
			this.plantsList.loadPlant(line);

			line = loadLine(out, "zombieList", true);
			this.zombieList = new ZombieList();
			this.zombieList.loadZombie(line);

		} catch (FileNotFoundException | FileContentsException e) {
			restore(oldGame);
			throw new CommandExecuteException("Load fail", e);
		} catch (AlteredFileException e) {
			throw new AlteredFileException(e);
		}
		return this;
	}

	private Game cln() {
		return this;
	}

	private void restore(Game game) {
		this.rand = game.rand;
		this.contCiclos = game.contCiclos;
		this.sunCoins = game.sunCoins;
		this.zombiesLeft.setRemainingZ(game.getZombiesLeft());

		this.zombieList = game.zombieList;
		this.plantsList = game.plantsList;

		this.numZ = game.numZ;
		this.loseGame = false;
		this.exit = false;
	}

	public void debugInfo() {
		System.out.println(draw());
		System.out.println(getLevel());
		System.out.println(get_rand());
	}

	public boolean isFull(int i, int j) {
		if (getPLInPosition(i, j) == null && getZInPosition(i, j) == null)
			return false;
		else
			return true;
	}

	public Plant getPLInPosition(int x, int y) {
		return this.plantsList.getPosition(x, y);
	}

	public Zombie getZInPosition(int x, int y) {
		return this.zombieList.getZPosition(x, y);
	}

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

	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList)
			throws IOException, FileContentsException {
		String[] words;
		String line = inStream.readLine().trim();
		// absence of the prefix is invalid
		if (!line.startsWith(prefix + ":"))
			throw new FileContentsException(wrongPrefixMsg + prefix);
		// cut the prefix and the following colon off the line then trim it to get
		// attribute contents
		String contentString = line.substring(prefix.length() + 1).trim();
		// the attribute contents are not empty
		if (!contentString.equals("")) {
			if (!isList) {
				// split non−list attribute contents into words
				// using 1−or−more−white−spaces as separator
				words = contentString.split("\\s+");
				// a non−list attribute with contents of more than one word is invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			} else
				// split list attribute contents into words
				// using comma+0−or−more−white−spaces as separator
				words = contentString.split(",\\s*");
			// the attribute contents are empty
		} else {
			// a non−list attribute with empty contents is invalid
			if (!isList)
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attribute with empty contents is valid; use a zero−length array to
			// store its words
			words = new String[0];
		}
		return words;
	}

	// ********************SETTERS Y GETTERS**************************

	public int getTamListPln() {
		return this.plantsList.getnObjects();
	}

	public int getTamListZmb() {
		return this.zombieList.getnObjects();
	}

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
		this.contCiclos += cont;
	}

	public int getSunCoins() {
		return this.sunCoins.getCoins();
	}

	public void setSunCoins(int sunCoins) {
		this.sunCoins.setCoins(sunCoins);
	}

	public int getZombiesLeft() {
		return this.zombiesLeft.getRemainingZ();
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

	public void setGamePrinter() {
		new ReleasePrinter(this, 4, 8);
	}

}
