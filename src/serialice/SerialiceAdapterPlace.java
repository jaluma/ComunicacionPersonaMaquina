package serialice;

import java.util.ArrayList;
import java.util.List;

import park.Place;

public class SerialiceAdapterPlace implements Serialice<Place> {
	
	public List<String> serialize(List<Place> list) {
		List<String> lines = new ArrayList<String>();
		for (Place e: list) {
			lines.add(e.serialize());
		}

		return lines;
	}

}
