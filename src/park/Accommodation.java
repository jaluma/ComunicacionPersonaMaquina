package park;

import java.util.Date;

import internationalization.Internationalization;
import logic.ListProduct;

public class Accommodation extends Product {

	private static final double BREAKFAST = 1.1;
	private TypeHotel type;
	private int stars;
	private Park park;
	private int num;
	private double price;
	private boolean breakfast;

	public Accommodation(String code, String type, int stars, String name, String codePark, int num, double price) {
		super(code, name);
		setType(type);
		setStars(stars);
		setPark(ListProduct.searchPark(codePark));
		setNum(num);
		setPrice(price);
	}

	public void loadData(int numberAdult, int numberChild, Date date, int days) {
		setNumberAdult(numberAdult);
		setNumberChild(numberChild);
		setDate(date);
		setDuration(days);
	}

	public void setBreakfast() {
		breakfast = true;
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

	private void setPark(Park park) {
		this.park = park;
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

	public String getCodePark() {
		return park.getName();
	}

	public double getPrice() {
		return price;
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		String str = "";
		str += Internationalization.getString("accom_subtitle") + ": " + code + " / " + type + " / " + name + " / "
				+ stars + " " + Internationalization.getString("stars") + " / " + park.getName()
				+ "\n";
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

}
