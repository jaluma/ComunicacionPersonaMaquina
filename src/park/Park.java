package park;

import fileUtil.AssertParam;

public class Park {
	
	private String code;
	private String name;
	private String country;
	private String city;
	private String description;
	
	public Park(String code, String name, String country, String city, String description) {
		setCode(code);
		setName(name);
		setCity(city);
		setCountry(country);
		setDescription(description);
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		AssertParam.assertIsEmptry(code);
		AssertParam.assertNoNullString(code);
		this.code = code;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		AssertParam.assertIsEmptry(name);
		AssertParam.assertNoNullString(name);
		this.name = name;
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
	
	public String serialize() {
		StringBuilder sb = new StringBuilder();
		sb.append(code).append("@").append(name).append("@").append(country).append("@").append(city).append("@").append(description);
		return sb.toString();
	}
	
	

}
