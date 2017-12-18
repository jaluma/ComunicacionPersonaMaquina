package park;

public class Accommodation {
	
	private String code;
	private TypeHotel type;
	private int stars;
	private String name;
	private String codePark;
	private double price;
	
	public Accommodation(String code, String type, int stars, String name, String codePark, double price)  {
		setCode(code);
		setType(type);
		setStars(stars);
		setCodePark(codePark);
		setPrice(price);
		setName(name);
	}

	private void setCode(String code) {
		this.code = code;
	}

	private void setType(String type) {
		this.type = Enum.valueOf(TypeHotel.class, type);
	}

	private void setStars(int stars) {
		this.stars = stars;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setCodePark(String codePark) {
		this.codePark = codePark;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type.toString();
	}

	public int getStars() {
		return stars;
	}

	public String getName() {
		return name;
	}

	public String getCodePark() {
		return codePark;
	}

	public double getPrice() {
		return price;
	}
	
	

}
