package park;

import java.io.Serializable;

import fileUtil.AssertParam;

public class Park implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private String country;
	private String city;
	private String description;
	private boolean sale;

	public Park(String code, String name, String country, String city, String description) {
		setCode(code);
		setName(name);
		setCity(city);
		setCountry(country);
		setDescription(description);
	}

	public void sale() {
		sale = true;
	}
	
	public void noSale() {
		sale = false;
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

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return name;
	}

}
