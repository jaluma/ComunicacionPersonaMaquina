package parser;

import fileUtil.IncorrectLineFormat;
import park.Package;
import park.Product;

public class PackageParser extends ParserAdapter<Product> {

	@Override
	public Product parseLine(String line) throws IncorrectLineFormat {
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
			throw new IncorrectLineFormat("ERROR");
		}
	}
	
	// <----------------------------------------------------------------------------------------------------->

		private void assertPackage(String[] lineArray) throws IncorrectLineFormat {
			if (lineArray.length != 7) {
				throw new IncorrectLineFormat(
						"ERROR: Line lenght's wrong.");
			}

		}

}
