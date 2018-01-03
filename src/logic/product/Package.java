package logic.product;

import internationalization.Internationalization;
import util.file.AssertParam;

public class Package extends Product {

	private static final long serialVersionUID = 1L;
	private Accommodation accom;
	private double priceAdult;
	private double priceChild;

	public Package(String code, String name, String codePark, String codeAccom, int duration, double priceAdult,
			double priceChild) {
		super(code, name, codePark);
		setAccom((Accommodation) ListProduct.searchProduct(codeAccom));
		setDuration(duration);
		setPriceAdult(priceAdult);
		setPriceChild(priceChild);
	}

	public String getCode() {
		return code;
	}

	public Accommodation getAccom() {
		return accom;
	}

	public double getPriceAdult() {
		return priceAdult;
	}

	public double getPriceChild() {
		return priceChild;
	}

	private void setAccom(Accommodation accom) {
		this.accom = accom;
	}

	private void setPriceAdult(double priceAdult) {
		AssertParam.assertNoNegative((int) priceAdult);
		this.priceAdult = priceAdult;
	}

	private void setPriceChild(double priceChild) {
		AssertParam.assertNoNegative((int) priceChild);
		this.priceChild = priceChild;
	}

	@Override
	public double getTotal() {
		return (numberAdult * priceAdult) + (numberChild * priceChild);
	}

	@Override
	public String toString() {
		String str = "";
		str += Internationalization.getString("package_subtitle") + ": " + code + " / " + name + " / " + park.getName()
				+ " / " + duration + " " + Internationalization.getString("days") + "\n";
		str += Internationalization.getString("initial_date") + ": " + Internationalization.getFormatDate(date) + "\n";
		str += Internationalization.getString("number_adult") + ": " + numberAdult + " / "
				+ Internationalization.getString("number_child") + ": " + numberChild + "\n";
		return str;
	}

	@Override
	public boolean isSale() {
		return park.isSale();
	}

	public String toString2() {
		return Internationalization.getString("package").toUpperCase() + ": " + getName();
	}

	public String toString3() {
		String str = super.toString3();
		str += String.format("<b>%s</b>: %s<br>", Internationalization.getString("name_accom").toUpperCase(),
				accom.getName());
		if (accom.getType() == TypeHotel.HO.toString())
			str += String.format("<b>%s</b>: %s<br>", Internationalization.getString("stars").toUpperCase(),
					accom.getStars());
		str += String.format("<b>%s</b>: %d<br>", Internationalization.getString("size").toUpperCase(), accom.getNum());
		str += String.format("<b>%s</b>: %d", Internationalization.getString("duration").toUpperCase(), duration);
		return str;
	}

}
