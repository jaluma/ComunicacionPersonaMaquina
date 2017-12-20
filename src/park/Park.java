package park;

import java.util.Date;

import fileUtil.AssertParam;

public class Park extends Product {

	private String country;
	private String city;
	private String description;
	private boolean sale;

	public Park(String code, String name, String country, String city, String description) {
		super(code, name);
		setCity(city);
		setCountry(country);
		setDescription(description);
	}

	public void sale() {
		sale = true;
	}

	public boolean isSale() {
		return sale;
	}

	public String getCountry() {
		return country;
	}

	private void setCountry(String country) {
		AssertParam.assertIsEmptry(country);
		AssertParam.assertNoNullString(country);
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) {
		AssertParam.assertIsEmptry(city);
		AssertParam.assertNoNullString(city);
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		AssertParam.assertIsEmptry(description);
		AssertParam.assertNoNullString(description);
		this.description = description;
	}

	@Override
	public void loadData(int numberAdult, int numberChild, Date date, int days) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
