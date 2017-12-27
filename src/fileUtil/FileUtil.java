package fileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {

	public static List<String> loadLines(String fileName) throws FileNotFoundException, IOException {
		List<String> lines = new LinkedList<String>();

		BufferedReader br = new BufferedReader(new FileReader(fileName));

		while (br.ready()) {
			lines.add(br.readLine());
		}

		br.close();

		return lines;
	}

	public static void saveToFile(String fileName, List<String> lines) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

		for (String line : lines) {
			bw.write(line);
			bw.newLine();
		}

		bw.close();
	}

	public static int find(String path, String searchString) { // Devuelve el numero de la linea
		int result = -1;
		Scanner in = null;
		int count = 0;
		try {
			in = new Scanner(new FileReader(new File(path)));
			while (in.hasNextLine()) {
				result = in.nextLine().indexOf(searchString);
				if (result != -1)
					break;
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				/* ignore */ }
		}
		return count;
	}
}