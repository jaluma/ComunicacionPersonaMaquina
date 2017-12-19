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
	
	public void add(Product product) {
		products.add(product);
	}

	@Override
	public String toString() {
		String str = "";
		str += Internationalization.getString("travels").toUpperCase() + "\t" + Internationalization.getString("company").toUpperCase() + "\n\n";
		str += Internationalization.getString("proof").toUpperCase() + " - " + Internationalization.getActualDate() + "\n";
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
		str += Internationalization.getString("package_title") + ": " + addTab(7) + Internationalization.getCurrency(getPrice(Package.class)) + "\n";
		str += Internationalization.getString("accom_title") + ": " + addTab(8) + Internationalization.getCurrency(getPrice(Accommodation.class)) + "\n";
		str += Internationalization.getString("tickets_title") + ": " + addTab(8) + Internationalization.getCurrency(getPrice(Ticket.class)) + "\n";
		
		if (checkOffer())
			str += Internationalization.getString("discount_title") + ": " + addTab(8) + Internationalization.getCurrency(getDiscount()) + "\n";
		str += "\n";
		
		str += Internationalization.getString("total_title") + ": " + addTab(8) + Internationalization.getCurrency(getTotal());
		return str;
	}
	
	private String addTab(int number) {
		String str = "";
		for (int i = 0; i < number; i++)
			str += "\t";
		return str;
	}
	
	private boolean checkOffer() {
		for (Product e : products) {
			if (e.isOffer())
				return true;
		}
		return false;
	}
	
	private double getDiscount() {
		double total = 0.0;
		for (Product e : products) {
			if (e.isOffer())
				total += e.discount();
		}
		return total;
	}
	
	private double getPrice(Class<?> c) {
		double total = 0.0;
		for (int i = 0; i < products.size();i++) {
			if (products.get(i).getClass().equals(c)) {			//instanceof
				total += products.get(i).getTotal();
			}
		}
		return total;
	}
	
	private double getTotal() {
		double total = 0.0;
		for (int i = 0; i < products.size(); i++) 
			total += products.get(i).getTotal();
		
		return total;
	}
}
