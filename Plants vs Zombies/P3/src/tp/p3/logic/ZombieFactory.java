package tp.p3.logic;

import java.util.Random;

import tp.p3.logic.objects.zombies.BucketheadZombie;
import tp.p3.logic.objects.zombies.CommonZombie;
import tp.p3.logic.objects.zombies.SportyZombie;
import tp.p3.logic.objects.zombies.Zombie;

public class ZombieFactory {

	static Random random = new Random();
	private static Zombie[] availableZombies = { new BucketheadZombie(getRandX(random), 7),
			new SportyZombie(getRandX(random), 7), new CommonZombie(getRandX(random), 7) };

	public static Zombie getZombie() {
		String zombieName = getRandZombie(random).getZombieName();
		for (Zombie z : availableZombies) {
			z = z.parse(zombieName, getRandX(random));
			if (z != null)
				return z;
		}
		return null;
	}

	public static Zombie getRandZombie(Random rand) {
		return availableZombies[rand.nextInt(availableZombies.length)];
	}
	
	public static Zombie getSavedZombies(String zombieName) {
		for (Zombie z : availableZombies) {
			z = z.parse(zombieName, getRandX(random));
			if (z != null)
				return z;
		}
		return null;
	}

	public static String listOfAvilableZombies() {
		String list = "These are the available zombies: \n";
		for (Zombie i : availableZombies) {
			list += i.toString() + "\n";
		}
		return list;
	}

	public static int getRandX(Random rand) {
		return rand.nextInt(4);
	}

}
