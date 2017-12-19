package parser;

import fileUtil.IncorrectLineFormat;
import park.Accommodation;
import park.Place;

public class ParserAccommodation extends ParserAdapter<Place> {

	public Place parseLine(String line) throws IncorrectLineFormat {
		String[] lineArray = line.split("\t");

		assertArray(lineArray);
		assertAccommodation(lineArray);

		// Guardado de la información leída

		try {
		String code = lineArray[0];
		String type = lineArray[1];
		int stars = Integer.parseInt(lineArray[2]);
		String name = lineArray[3];
		String codeAccom = lineArray[4];
		int num = Integer.parseInt(lineArray[5]);
		double price = Double.parseDouble(lineArray[6]);
		
		return new Accommodation(code, type, stars, name, codeAccom, num, price);
		} catch (NumberFormatException e) {
			throw new IncorrectLineFormat("ERROR");
		}
	}

	// <----------------------------------------------------------------------------------------------------->

	private void assertAccommodation(String[] lineArray) throws IncorrectLineFormat {
		if (lineArray.length != 7) {
			throw new IncorrectLineFormat(
					"ERROR: Line lenght's wrong.");
		}

	}

}