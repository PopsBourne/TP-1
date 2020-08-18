package tp.p2.logic;



import java.util.Random;

import tp.p2.logic.objects.zombies.BucketheadZombie;
import tp.p2.logic.objects.zombies.SportyZombie;
import tp.p2.logic.objects.zombies.CommonZombie;
import tp.p2.logic.objects.zombies.Zombie;

public class ZombieFactory {

	static Random randomno = new Random();
	private static Zombie[] availableZombies = { new BucketheadZombie(getRandX(randomno),7), new SportyZombie(getRandX(randomno),7), new CommonZombie(getRandX(randomno),7) };
	
	
	
	//ZOMBIE MANAGER
	
	public  Zombie getZombie(String zombieName) {
		if (zombieName.equalsIgnoreCase("CommonZombie") || zombieName.equalsIgnoreCase("z"))
			return new CommonZombie(getRandX(randomno),7);
		else if (zombieName.equalsIgnoreCase("SportyZombie") || zombieName.equalsIgnoreCase("x"))
			return new SportyZombie(getRandX(randomno),7);
		else if (zombieName.equalsIgnoreCase("BucketheadZombie") || zombieName.equalsIgnoreCase("w"))
			return new BucketheadZombie(getRandX(randomno),7);
		return null;
	}
	
	public static Zombie getRandZombie(Random rand) {
		return availableZombies[rand.nextInt(availableZombies.length)];//-1
	}
	
	public static int getRandX(Random rand) {
		return rand.nextInt(4);
	}
	
	//public Zombie getZombie()
	
	// Lista de todas las plantas disponibles
		public static String listOfAvilableZombies() {
			String list = "These are the available zombies: \n";
			for (Zombie i : availableZombies) {
				list += i.toString() + "\n";
			}
			return list;
		}
}
