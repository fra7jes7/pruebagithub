package reserva.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EliminarObjetoList2 {

	public EliminarObjetoList2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] bands = { "Jesus", "null", "Romeo", "null", "Roberto" };
		List<String> bandList = new ArrayList<String>();

		// add elements from String array to List
		for (String band : bands) {
			bandList.add(band);
		}

		String[] undesirableBands = { "Nirvana", "Led Zepelling", "The Strokes", "Jamiroquai" };
		List<String> undesirableList = new ArrayList<String>();

		// add elements from String array to List
		for (String undesirableBand : undesirableBands) {
			undesirableList.add(undesirableBand);
		}

		System.out.println("List of bands has: ");
		for (int count = 0; count < bandList.size(); count++) {
			System.out.printf("%s ", bandList.get(count));
		}

		Iterator<String> iteratorBand = bandList.iterator();

		while (iteratorBand.hasNext()) {
			if ((iteratorBand.next()).equals("null")) {
				iteratorBand.remove();
			}
		}
		// removeBands(bandList);
		System.out.println("\n\nI just wish these bands: ");

		for (int count = 0; count < bandList.size(); count++) {
			System.out.printf("%s ", bandList.get(count));
		}
	}

	public static void removeBands(Collection<String> bands) {
		Iterator<String> iteratorBand = bands.iterator();

		while (iteratorBand.hasNext()) {
			if ((iteratorBand.next()).equals("null")) {
				iteratorBand.remove();
			}
		}
	}
}
