package park;

import java.util.Date;

public abstract class Product {

	private static final double DISCOUNT = 0.2;
	protected String code;
	protected String name;
	protected Date initialDate;
	protected int numberAdult;
	protected int numberChild;
	protected Date date;
	protected int duration;

	public Product(String code, String name) {
		setCode(code);
		setName(name);
		setNumberAdult(0);
		setNumberChild(0);
	}

	public abstract void loadData(int numberAdult, int numberChild, Date date, int days);

	protected void setNumberChild(int i) {
		this.numberAdult = i;
	}

	protected void setNumberAdult(int i) {
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

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
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
