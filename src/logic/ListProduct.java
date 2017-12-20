package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import parser.PackageParser;
import parser.ParserAccommodation;
import parser.ParserPark;
import parser.TicketParser;
import fileUtil.FileUtil;
import fileUtil.IncorrectLineFormat;
import park.Product;
import park.Ticket;
import park.Package;
import park.Park;

public class ListProduct {

	private static List<Product> products = loadProducts();

	public static List<Product> loadProducts() {
		List<Product> productsList = new ArrayList<Product>();
		List<String> nameFiles = Arrays.asList("alojamientos.dat", "tematicos.dat", "entradas.dat", "paquetes.dat");
		int randomIndex = randomOffer();
		for (String file : nameFiles) {
			try {
				List<String> lines = new FileUtil().loadLines("files/" + file);
				for (String line : lines) {
					if (file.equals(nameFiles.get(0))) {
						productsList.add(new ParserAccommodation().parseLine(line));
					} else if (file.equals(nameFiles.get(1))) {
						Product park = new ParserPark().parseLine(line);
						if (randomIndex == lines.indexOf(line))
							((Park) park).sale();
						productsList.add(park);
					} else if (file.equals(nameFiles.get(2))) {
						productsList.add(new TicketParser().parseLine(line));
					} else if (file.equals(nameFiles.get(3))) {
						productsList.add(new PackageParser().parseLine(line));
					} else {
						throw new IncorrectLineFormat("ERROR");
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

	public static Product search(String code) { // codePark debe ser correcto
		for (Product e : products) {
			if (e.getCode().equals(code))
				return e;
			if (e.getClass().equals(Package.class)) {
				Package pk = ((Package) e);
				if (pk.getPark().getCode().equals(code) || pk.getAccom().getCode().equals(code))
					return e;
			}
		}
		return null; // si entra por aqui codigo incorrecto, se supone que todo esta correcto
	}

	public static Ticket searchTicket(String codePark) { // codePark debe ser correcto
		for (Product e : products) {
			if (e instanceof Ticket && ((Ticket) e).getCodePark().equals(codePark))
				return (Ticket) e;
		}
		throw new IllegalArgumentException(); // si entra por aqui codigo incorrecto, se supone que todo esta correcto
	}

	private static int randomOffer() {
		Random rd = new Random();
		try {
			return rd.nextInt(new FileUtil().loadLines("files/tematicos.dat").size());
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

	}
}
