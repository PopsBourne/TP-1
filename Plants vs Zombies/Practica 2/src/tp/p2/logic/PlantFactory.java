package tp.p2.logic;

import tp.p2.logic.objects.plants.Wallnut;
import tp.p2.logic.objects.plants.Peashooter;
import tp.p2.logic.objects.plants.Cherrybomb;
import tp.p2.logic.objects.plants.Plant;
import tp.p2.logic.objects.plants.Sunflower;

public class PlantFactory {

	private static Plant[] availablePlants = { new Sunflower(0,0), new Peashooter(0,0), new Cherrybomb(0,0),
			new Wallnut(0,0) };

	public static Plant getPlant(String plantName) {

		if (plantName.equalsIgnoreCase("sunflower") || plantName.equalsIgnoreCase("s"))
			return new Sunflower(0,0);
		else if (plantName.equalsIgnoreCase("peashooter") || plantName.equalsIgnoreCase("p"))
			return new Peashooter(0,0);
		else if (plantName.equalsIgnoreCase("nuez") || plantName.equalsIgnoreCase("n"))
			return new Wallnut(0,0);
		else if (plantName.equalsIgnoreCase("petacereza") || plantName.equalsIgnoreCase("c"))
			return new Cherrybomb(0,0);
		return null;
	}

	// Lista de todas las plantas disponibles
	public static String listOfAvilablePlants() {
		String list = "These are the available plants: \n";
		for (Plant i : availablePlants) {
			list += i.toString() + "\n";
		}
		return list;
	}
}