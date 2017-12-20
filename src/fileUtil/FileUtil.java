package fileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {

	public List<String> loadLines(String fileName) throws FileNotFoundException, IOException {
		List<String> lines = new LinkedList<String>();

		BufferedReader br = new BufferedReader(new FileReader(fileName));

		while (br.ready()) {
			lines.add(br.readLine());
		}

		br.close();

		return lines;
	}

	public void saveToFile(String fileName, List<String> lines) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

		for (String line : lines) {
			bw.write(line);
			bw.newLine();
		}

		bw.close();
	}
}