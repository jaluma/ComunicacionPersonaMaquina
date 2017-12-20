package parser;

import fileUtil.IncorrectLineFormat;
import park.Ticket;

public class TicketParser extends ParserAdapter<Ticket> {

	public Ticket parseLine(String line) throws IncorrectLineFormat {
		String[] lineArray = line.split("@");

		assertArray(lineArray);
		assertTicket(lineArray);

		// Guardado de la información leída

		try {
			String codeTicket = lineArray[0];
			String codePark = lineArray[1];
			double priceAdult = Double.parseDouble(lineArray[2]);
			double priceChild = Double.parseDouble(lineArray[3]);

			return new Ticket(codeTicket, codePark, priceAdult, priceChild);
		} catch (NumberFormatException e) {
			throw new IncorrectLineFormat("ERROR");
		}
	}

	// <----------------------------------------------------------------------------------------------------->

	private void assertTicket(String[] lineArray) throws IncorrectLineFormat {
		if (lineArray.length != 4) {
			throw new IncorrectLineFormat("ERROR: Line lenght's wrong.");
		}

	}

}