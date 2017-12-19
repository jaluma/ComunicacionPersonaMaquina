package serialice;

import java.util.ArrayList;
import java.util.List;

import park.Ticket;

public class SerialiceTicket implements Serialice<Ticket> {

	@Override
	public List<String> serialize(List<Ticket> list) {
		List<String> lines = new ArrayList<String>();
		for (Ticket e: list) {
			lines.add(e.serialize());
		}

		return lines;
	}

}
