package tp.p3.logic;

import tp.p3.logic.objects.plants.Cherrybomb;
import tp.p3.logic.objects.plants.Peashooter;
import tp.p3.logic.objects.plants.Plant;
import tp.p3.logic.objects.plants.Sunflower;
import tp.p3.logic.objects.plants.Wallnut;

public class PlantFactory {

	private static Plant[] availablePlants = { new Sunflower(0, 0), new Peashooter(0, 0), new Cherrybomb(0, 0),
			new Wallnut(0, 0) };

	public static Plant getPlant(String plantName) {

		for (Plant p : availablePlants) {
			p = p.parse(plantName);
			if (p != null)
				return p;
		}
		return null;
	}

	public static String listOfAvilablePlants() {
		String list = "These are the available plants: \n";
		for (Plant i : availablePlants) {
			list += i.toString() + "\n";
		}
		return list;
	}
}