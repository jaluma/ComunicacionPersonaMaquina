package logic.parser;

import java.util.List;

import util.file.IncorrectLineFormatException;

public interface Parser<T> {

	public List<T> parser(List<String> lines);

	public T parseLine(String line) throws IncorrectLineFormatException;

}
