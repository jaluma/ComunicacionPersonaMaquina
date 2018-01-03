package logic.parser;

import logic.park.Park;
import util.file.IncorrectLineFormatException;

public class ParserPark extends ParserAdapter<Park> {

	public Park parseLine(String line) throws IncorrectLineFormatException {
		String[] lineArray = line.split("@");

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

	private void assertPark(String[] lineArray) throws IncorrectLineFormatException {
		if (lineArray.length != 5) {
			throw new IncorrectLineFormatException("ERROR: Line lenght's wrong.");
		}

	}
}