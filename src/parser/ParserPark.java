package parser;

import fileUtil.IncorrectLineFormat;
import park.Park;
import park.Place;

public class ParserPark extends ParserAdapter<Place> {

	public Place parseLine(String line) throws IncorrectLineFormat {
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

	private void assertPark(String[] lineArray) throws IncorrectLineFormat {
		if (lineArray.length != 5) {
			throw new IncorrectLineFormat(
					"ERROR: Line lenght's wrong.");
		}

	}
}