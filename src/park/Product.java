package park;

import java.util.Date;

import logic.ListProduct;

public abstract class Product {

	private static final double DISCOUNT = 0.2;
	protected String code;
	protected String name;
	protected Date initialDate;
	protected int numberAdult;
	protected int numberChild;
	protected Date date;
	protected int duration;
	protected Park park;

	public Product(String code, String name, String codePark) {
		setCode(code);
		setName(name);
		setNumberAdult(0);
		setNumberChild(0);
		setPark(ListProduct.searchPark(codePark));
	}

	public void loadData(int numberAdult, int numberChild, Date date, int days) {
		if (numberAdult > 0)
			setNumberAdult(numberAdult);
		if (numberChild >= 0)
			setNumberChild(numberChild);
		if (date != null)
			setDate(date);
		if (days >= 0 && ! (this instanceof Package))
			setDuration(days);
	}

	public void setNumberChild(int i) {
		this.numberAdult = i;
	}

	public void setNumberAdult(int i) {
		this.numberChild = i;
	}

	protected void setDate(Date date) {
		this.date = date;
	}

	protected void setInitialDate(Date date) {
		this.initialDate = date;
	}

	protected void setDuration(int duration) {
		this.duration = duration;
	}

	protected void setCode(String code) {
		this.code = code;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected void setPark(Park park) {
		this.park = park;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public Park getPark() {
		return park;
	}

	public double getDiscount() {
		if (isSale())
			return getTotal() * DISCOUNT;
		return 0.0;
	}

	public abstract double getTotal();

	public abstract boolean isSale();

	public abstract String toString();

}
