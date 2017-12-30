package parser;

import fileUtil.IncorrectLineFormatException;
import product.Accommodation;
import product.Product;

public class ParserAccommodation extends ParserAdapter<Product> {

	public Product parseLine(String line) throws IncorrectLineFormatException {
		String[] lineArray = line.split("@");

		assertArray(lineArray);
		assertAccommodation(lineArray);

		// Guardado de la información leída

		try {
			String code = lineArray[0];
			String type = lineArray[1];
			int stars = Integer.parseInt(lineArray[2]);
			String name = lineArray[3];
			String codePark = lineArray[4];
			int num = Integer.parseInt(lineArray[5]);
			double price = Double.parseDouble(lineArray[6]);
			

			return new Accommodation(code, type, stars, name, codePark, num, price);
		} catch (NumberFormatException e) {
			throw new IncorrectLineFormatException("ERROR");
		}
	}

	// <----------------------------------------------------------------------------------------------------->

	private void assertAccommodation(String[] lineArray) throws IncorrectLineFormatException {
		if (lineArray.length != 7) {
			throw new IncorrectLineFormatException("ERROR: Line lenght's wrong.");
		}

	}

}