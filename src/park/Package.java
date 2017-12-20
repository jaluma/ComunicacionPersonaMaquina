package park;

import java.util.Date;

import fileUtil.AssertParam;
import internationalization.Internationalization;
import logic.ListProduct;

public class Package extends Product {

	private Park park;
	private Accommodation accom;
	private double priceAdult;
	private double priceChild;

	public Package(String code, String name, String codePark, String codeAccom, int duration, double priceAdult,
			double priceChild) {
		super(code, name);
		setPark((Park) ListProduct.search(codePark));
		setAccom((Accommodation) ListProduct.search(codeAccom));
		setDuration(duration);
		setPriceAdult(priceAdult);
		setPriceChild(priceChild);
	}

	// no se usa days
	public void loadData(int numberAdult, int numberChild, Date date, int days) {
		setNumberAdult(numberAdult);
		setNumberChild(numberChild);
		setDate(date);
	}

	public String getCode() {
		return code;
	}

	public Park getPark() {
		return park;
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

	private void setPark(Park park) {
		this.park = park;
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
		return ((Park) ListProduct.search(park.getCode())).isSale();
	}

}