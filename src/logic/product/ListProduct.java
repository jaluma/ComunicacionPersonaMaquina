package logic.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import logic.park.Park;
import logic.parser.ParserAccommodation;
import logic.parser.ParserPackage;
import logic.parser.ParserPark;
import logic.parser.TicketParser;
import util.file.FileUtil;
import util.file.IncorrectLineFormatException;

public class ListProduct {

	public static List<Park> parks = loadPark();
	public static List<Product> products = loadProducts();
	public static List<String> places = Arrays.asList(loadPlaces());

	private static List<Product> loadProducts() {
		List<Product> productsList = new ArrayList<Product>();
		List<String> nameFiles = Arrays.asList("alojamientos.dat", "entradas.dat", "paquetes.dat");
		for (String file : nameFiles) {
			try {
				List<String> lines = FileUtil.loadLines("files/" + file);
				for (String line : lines) {
					if (file.equals(nameFiles.get(0))) {
						productsList.add(new ParserAccommodation().parseLine(line));
					} else if (file.equals(nameFiles.get(1))) {
						productsList.add(new TicketParser().parseLine(line));
					} else if (file.equals(nameFiles.get(2))) {
						productsList.add(new ParserPackage().parseLine(line));
					} else {
						throw new IncorrectLineFormatException("ERROR");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			products = productsList; // cada vez que sale del bucle se hace un guardado de productos para que puedan
										// ir luego a buscarlos
		}
		return productsList;
	}

	private static List<Park> loadPark() {
		List<Park> parkList = new ArrayList<Park>();
		int randomIndex = randomOffer();
		try {
			List<String> lines = FileUtil.loadLines("files/tematicos.dat");
			for (String line : lines) {
				Park park = new ParserPark().parseLine(line);
				if (randomIndex == lines.indexOf(line))
					((Park) park).sale();
				parkList.add(park);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkList;
	}

	public static String[] loadPlaces() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < parks.size(); i++) {
			list.add(parks.get(i).getCity());
			list.add(parks.get(i).getCountry());
		}
		list = list.stream().distinct().collect(Collectors.toList());
		Collections.sort(list);
		return list.toArray(new String[list.size()]);
	}

	public static Product searchProduct(String code) { // codePark debe ser correcto
		for (Product e : products) {
			if (e.getCode().equals(code))
				return e;
		}
		throw new IllegalArgumentException(); // si entra por aqui codigo incorrecto, se supone que todo esta correcto
	}

	public static Park searchPark(String codePark) {
		for (Park park : parks) {
			if (park.getCode().equals(codePark))
				return park;
		}
		throw new IllegalArgumentException();
	}

	private static int randomOffer() {
		Random rd = new Random();
		try {
			return rd.nextInt(FileUtil.loadLines("files/tematicos.dat").size());
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

	}

	public static int getMaxPrice() {
		double max = 0;
		for (Product e : products) {
			double price = e.getTotal();
			if (price > max)
				max = price;
		}
		return (int) max + 1;
	}

	public static int getMinPrice() {
		double min = Double.MAX_VALUE;
		for (Product e : products) {
			double price = e.getTotal();
			if (price < min)
				min = price;
		}
		return (int) min;
	}
}
