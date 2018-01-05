package logic.product;

import gui.guiUtil.internationalization.Internationalization;

public class Accommodation extends Product {

	private static final long serialVersionUID = 1L;
	private static final double BREAKFAST = 1.1;
	private TypeHotel type;
	private int stars;
	private int num;
	private double price;
	private boolean breakfast;

	public Accommodation(String code, String type, int stars, String name, String codePark, int num, double price) {
		super(code, name, codePark);
		setType(type);
		setStars(stars);
		setNum(num);
		setPrice(price);
	}

	public void setBreakfast(boolean bool) {
		breakfast = bool;
	}

	private void setNum(int num) {
		this.num = num;
	}

	private void setType(String type) {
		this.type = Enum.valueOf(TypeHotel.class, type);
	}

	private void setStars(int stars) {
		this.stars = stars;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type.toString();
	}

	public int getStars() {
		return stars;
	}

	public double getPrice() {
		return price;
	}

	public int getNum() {
		return num;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	@Override
	public String toString() {
		String str = "";
		str += Internationalization.getString("accom_subtitle") + ": " + code + " / " + type + " / " + name + " / "
				+ stars + " " + Internationalization.getString("stars").toLowerCase() + " / " + park.getName() + "\n";
		str += Internationalization.getString("initial_date") + ": " + Internationalization.getFormatDate(date) + " / "
				+ Internationalization.getString("number_days") + ": " + duration + printBreakfast() + "\n";
		str += Internationalization.getString("number_people") + ": " + (numberAdult + numberChild) + "\n";
		return str;
	}

	private String printBreakfast() {
		String str = "";
		if (type.equals(TypeHotel.HO)) {
			str += " / " + Internationalization.getString("breakfast") + ": ";
			if (breakfast)
				str += Internationalization.getString("yes_one_letter");
			else
				str += Internationalization.getString("no_one_letter");
		}
		return str;
	}

	@Override
	public double getTotal() {
		int numPeople = numberAdult + numberChild;
		double total = price * duration;

		if (type.equals(TypeHotel.HO))
			total *= numPeople;

		if (breakfast) // cost breakfast
			total *= BREAKFAST;

		return total;
	}

	@Override
	public boolean isSale() {
		return park.isSale();
	}

	public String toString2() {
		return Internationalization.getString("accom").toUpperCase() + ": " + getName();
	}

	public String toString3() {
		String str = super.toString3();
		if (type == TypeHotel.HO)
			str += String.format("<b>%s</b>: %d<br>", Internationalization.getString("stars").toUpperCase(), stars);
		str += String.format("<b>%s</b>: %d<br>", Internationalization.getString("size").toUpperCase(), num);
		return str;
	}

}
