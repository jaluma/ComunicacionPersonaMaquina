package parser;

import java.util.LinkedList;
import java.util.List;

import fileUtil.IncorrectLineFormatException;

public abstract class ParserAdapter<T> implements Parser<T> {

	public List<T> parser(List<String> lines) {
		List<T> list = new LinkedList<T>();

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			try {
				assertLine(line);

				list.add(parseLine(line));
			} catch (IncorrectLineFormatException e) {
				System.err.println("ERROR: Line " + (i + 1) + ". " + e.getMessage());
			}
		}

		return list;
	}

	public abstract T parseLine(String line) throws IncorrectLineFormatException;

	// <----------------------------------------------------------------------------------------------------->

	protected void assertLine(String line) throws IncorrectLineFormatException {
		if (line.trim().length() == 0)
			throw new IncorrectLineFormatException("Line is emptry");
	}

	protected void assertArray(String[] lineArray) throws IncorrectLineFormatException {
		if (lineArray.length == 0) {
			throw new IncorrectLineFormatException("ERROR: Line lenght's wrong.");
		}

	}

}
