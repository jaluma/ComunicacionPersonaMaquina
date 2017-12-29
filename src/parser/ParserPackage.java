package parser;

import fileUtil.IncorrectLineFormatException;
import product.Package;
import product.Product;

public class ParserPackage extends ParserAdapter<Product> {

	@Override
	public Product parseLine(String line) throws IncorrectLineFormatException {
		String[] lineArray = line.split("@");

		assertArray(lineArray);
		assertPackage(lineArray);

		// Guardado de la información leída

		try {
			String code = lineArray[0];
			String name = lineArray[1];
			String codePark = lineArray[2];
			String codeAccom = lineArray[3];
			int duration = Integer.parseInt(lineArray[4]);
			double priceAdult = Double.parseDouble(lineArray[5]);
			double priceChild = Double.parseDouble(lineArray[6]);

			return new Package(code, name, codePark, codeAccom, duration, priceAdult, priceChild);

		} catch (NumberFormatException e) {
			throw new IncorrectLineFormatException("ERROR");
		}
	}

	// <----------------------------------------------------------------------------------------------------->

	private void assertPackage(String[] lineArray) throws IncorrectLineFormatException {
		if (lineArray.length != 7) {
			throw new IncorrectLineFormatException("ERROR: Line lenght's wrong.");
		}

	}

}
