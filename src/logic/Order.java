package logic;

import park.Accommodation;
import park.Package;
import park.Product;
import park.Ticket;

import java.util.*;

import internationalization.Internationalization;

public class Order {
	private String name;
	private String dni;
	private List<Product> products;

	public Order(String name, String dni) {
		setName(name);
		setDni(dni);
		products = new ArrayList<Product>();
	}

	private void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getItems() {
		return products.size();
	}

	public void add(String code, int numberAdult, int numberChild, Date date, int days) {
		Product productList = ListProduct.search(code);
		productList.loadData(numberAdult, numberChild, date, days);
		products.add(productList);
	}

	@Override
	public String toString() {
		String str = "";
		str += Internationalization.getString("travels").toUpperCase() + "\t"
				+ Internationalization.getString("company").toUpperCase() + "\n\n";
		str += Internationalization.getString("proof").toUpperCase() + " - " + Internationalization.getActualDate()
				+ "\n";
		str += "------------------------------------------------------------------------------\n";
		str += dni + " - " + name.toUpperCase() + "\n\n";
		str += "**** " + Internationalization.getString("data_reservations").toUpperCase() + " ****\n\n";
		str += "** " + Internationalization.getString("package_title").toUpperCase() + " **\n";
		for (Product e : products) {
			if (e instanceof Package)
				str += e.toString() + "\n";
		}
		str += "\n";
		str += "** " + Internationalization.getString("accom_title").toUpperCase() + " **\n";
		for (Product e : products) {
			if (e instanceof Accommodation)
				str += e.toString() + "\n";
		}
		str += "\n";
		str += "** " + Internationalization.getString("tickets_title").toUpperCase() + " **\n";
		for (Product e : products) {
			if (e instanceof Ticket)
				str += e.toString() + "\n";
		}
		str += "\n";
		str += "**** " + Internationalization.getString("paid_title").toUpperCase() + " ****\n\n";
		str += String.format("%-30s %31s %15s \n", Internationalization.getString("package_title"), " ",
				Internationalization.getCurrency(getPrice(Package.class)));
		str += String.format("%-30s %31s %15s \n", Internationalization.getString("accom_title"), " ",
				Internationalization.getCurrency(getPrice(Accommodation.class)));
		str += String.format("%-30s %31s %15s \n", Internationalization.getString("tickets_title"), " ",
				Internationalization.getCurrency(getPrice(Ticket.class)));

		if (checkOffer())
			str += String.format("%-30s %31s %15s \n", Internationalization.getString("discount_title"), " ",
					Internationalization.getCurrency(getDiscount()));
		str += "\n";

		str += String.format("%-30s %31s %15s \n", Internationalization.getString("total_title"), " ",
				Internationalization.getCurrency(getTotal()));
		return str;
	}

	private boolean checkOffer() {
		for (Product e : products) {
			if (e.isSale())
				return true;
		}
		return false;
	}

	private double getDiscount() {
		double total = 0.0;
		for (Product e : products) {
			if (e.isSale())
				total += e.getDiscount();
		}
		return total;
	}

	private double getPrice(Class<?> c) {
		double total = 0.0;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getClass().equals(c)) { // instanceof
				total += products.get(i).getTotal();
			}
		}
		return total;
	}

	private double getTotal() {
		double total = 0.0;
		for (int i = 0; i < products.size(); i++)
			total += products.get(i).getTotal();

		return total - getDiscount();
	}
}
