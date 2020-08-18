package tp.p1.logic;

import java.util.Random;

public class ZombieManager {

	private int remainingZ;
	private boolean zombieAdded;
	private double frq;
	private Random rand;

	public ZombieManager(int numZ, double frq, Random rand) {
		this.remainingZ = numZ;
		this.frq = frq;
		this.rand =rand;
	}

	public boolean randomZombies() {
		double random = this.rand.nextInt(10);
		double n = 10-this.frq*10; 
		if (random >= n)
			this.zombieAdded = true;
		else
			this.zombieAdded = false;
		return this.zombieAdded;
	}

	

	public int getRemainingZ() {
		return remainingZ;
	}

	public int setRemainingZ(int remainingZ) {
		return this.remainingZ = remainingZ;
	}


}
