package order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import internationalization.Internationalization;
import product.Accommodation;
import product.ListProduct;
import product.Package;
import product.Product;
import product.Ticket;

public class Order {
	private String name;
	private String dni;
	private String obs;
	private List<Product> products;

	public Order() {
		setName("");
		setDni("");
		setObs("");
		products = new ArrayList<Product>();
	}

	public Order(Order order, String name, String dni, String obs) {
		this();
		if (order != null)
			products = order.getProducts();
		if (!name.equals(" ") || !name.isEmpty())
			setName(name);
		if (!dni.equals(" ") || !dni.isEmpty())
			setDni(dni);
		if (!obs.equals(" ") || !obs.isEmpty())
			setObs(obs);
	}

	public String getObs() {
		return obs;
	}

	private void setObs(String obs) {
		this.obs = obs;
	}

	private void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public String getDni() {
		return dni;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getItems() {
		return products.size();
	}

	public Product getProduct(int i) {
		return products.get(i);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void add(String code, int numberAdult, int numberChild, Date date, int days) {
		Product productList = ListProduct.searchProduct(code);
		productList.loadData(numberAdult, numberChild, date, days);
		products.add(productList);
	}

	public void add(String code) {
		Product productList = ListProduct.searchProduct(code);
		products.add(productList);
	}

	public void add(Product product) {
		products.add(product);
	}

	public void remove(String code) {
		products.remove(ListProduct.searchProduct(code));
	}

	public void remove(Product product) {
		products.remove(product);
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
		str += String.format("%-30s %31s %15s \n", Internationalization.getString("package_title").toUpperCase(), " ",
				Internationalization.getCurrency(getPrice(Package.class)));
		str += String.format("%-30s %31s %15s \n", Internationalization.getString("accom_title").toUpperCase(), " ",
				Internationalization.getCurrency(getPrice(Accommodation.class)));
		str += String.format("%-30s %31s %15s \n", Internationalization.getString("tickets_title").toUpperCase(), " ",
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

	public double getDiscount() {
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

	public double getTotal() {
		double total = 0.0;
		for (int i = 0; i < products.size(); i++)
			total += products.get(i).getTotal();

		return total - getDiscount();
	}
}
