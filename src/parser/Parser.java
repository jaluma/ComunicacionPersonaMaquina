package parser;

import java.util.List;

import fileUtil.IncorrectLineFormat;

public interface Parser<T> {

	public List<T> parser(List<String> lines);

	public T parseLine(String line) throws IncorrectLineFormat;

}
