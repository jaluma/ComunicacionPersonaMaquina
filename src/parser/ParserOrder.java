package parser;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import fileUtil.FileUtil;
import fileUtil.IncorrectOrderException;
import guiUtil.CopyObject;
import internationalization.Internationalization;
import order.Order;
import product.Accommodation;
import product.ListProduct;
import product.Package;
import product.Product;
import product.Ticket;

public class ParserOrder {

	public Order parser(String path) throws IncorrectOrderException {
		List<String> lines;
		Order order = null;
		try {
			lines = FileUtil.loadLines(path);

			String[] aux = lines.get(4).split(" - ");

			String name = aux[1];
			String dni = aux[0];
			String obs = "";

			order = new Order(null, name, dni, obs);
			for (Product e : ListProduct.products) {
				int indexLine = FileUtil.find(path, e.getCode());
				if (indexLine != -1) {
					Product product = ListProduct.searchProduct(e.getCode());

					int numberAdult = 0;
					int numberChild = 0;
					int days = 0;
					Date date = getDate(lines, indexLine);

					if (product instanceof Package) {
						numberAdult = getNumberAdult(lines, indexLine);
						numberChild = getNumberChild(lines, indexLine);
					} else if (product instanceof Ticket) {
						numberAdult = getNumberAdult(lines, indexLine);
						numberChild = getNumberChild(lines, indexLine);
						days = getDays(lines, indexLine);
					} else if (product instanceof Accommodation) {// puede estar mal
						numberAdult = getNumberAdult(lines, indexLine);
						days = getDays(lines, indexLine);
						((Accommodation)product).setBreakfast(getBreakfast(lines, indexLine));
					}

					product.loadData(numberAdult, numberChild, date, days);

					order.add(CopyObject.copy(product));

				}

			}

		} catch (IOException e1) {
			throw new IncorrectOrderException();
		}

		return order;
	}

	private int getNumberAdult(List<String> lines, int indexLine) {
		String line = lines.get(indexLine + 2).split(" / ")[0].split(": ")[1];
		return Integer.parseInt(line);
	}

	private int getNumberChild(List<String> lines, int indexLine) {
		String line = lines.get(indexLine + 2).split(" / ")[1].split(": ")[1];
		return Integer.parseInt(line);
	}

	private int getDays(List<String> lines, int indexLine) {
		String line = lines.get(indexLine + 1).split(" / ")[1].split(": ")[1];
		return Integer.parseInt(line);
	}

	private Date getDate(List<String> lines, int indexLine) {
		String line = lines.get(indexLine + 1).split(" / ")[0].split(": ")[1];
		return Internationalization.getDate(line);
	}
	
	private boolean getBreakfast(List<String> lines, int indexLine) {
		String line = lines.get(indexLine + 1).split(" / ")[2].split(": ")[1];
		if (Internationalization.getString("no_one_letter").toUpperCase().equals(line.toUpperCase()))
			return false;
		else
			return true;
	}
}