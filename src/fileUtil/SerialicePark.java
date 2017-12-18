package fileUtil;

import java.util.ArrayList;
import java.util.List;

import park.Park;

public class SerialicePark {
	
	public List<String> serialize(List<Park> parks) {
		List<String> lines = new ArrayList<String>();
		for (Park park: parks) {
			lines.add(park.serialize());
		}

		return lines;
	}

}
