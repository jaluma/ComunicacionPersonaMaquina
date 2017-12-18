package fileUtil;

import java.util.LinkedList;
import java.util.List;

import park.Park;

public class ParkParser {

	public List<Park> parser(List<String> lines) {
		List<Park> Parks = new LinkedList<Park>();

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			try {
				assertLine(line);

				Parks.add(parseLine(line));
			} catch (IncorrectLineFormat e) {
				System.err.println("ERROR: Line " + (i + 1) + ". "
						+ e.getMessage());
			}
		}

		return Parks;
	}

	private Park parseLine(String line) throws IncorrectLineFormat {
		String[] lineArray = line.split("\t");

		assertArray(lineArray);
		assertPark(lineArray);

		// Guardado de la información leída

		String code = lineArray[0];
		String name = lineArray[1];
		String country = lineArray[2];
		String city = lineArray[3];
		String description = lineArray[4];
		
		return new Park(code, name, country, city, description);
	}

		

	// <----------------------------------------------------------------------------------------------------->

	public void assertLine(String line) throws IncorrectLineFormat {
		if (line.trim().length() == 0)
			throw new IncorrectLineFormat("Line is emptry");
	}

	private void assertPark(String[] lineArray) throws IncorrectLineFormat {
		if (lineArray.length != 5) {
			throw new IncorrectLineFormat(
					"ERROR: Line lenght's wrong.");
		}

	}

	private void assertArray(String[] lineArray) throws IncorrectLineFormat {
		if (lineArray.length == 0) {
			throw new IncorrectLineFormat("ERROR: Line lenght's wrong.");
		}

	}
}