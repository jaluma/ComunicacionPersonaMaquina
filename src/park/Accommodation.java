package park;

public class Accommodation extends Place {
	
	
	private TypeHotel type;
	private int stars;
	private String codeAccom;
	private int num;
	private double price;
	
	public Accommodation(String code, String type, int stars, String name, String codeAccom, int num, double price)  {
		super(code, name);
		setType(type);
		setStars(stars);
		setCodePark(codeAccom);
		setNum(num);
		setPrice(price);
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

	private void setCodePark(String codeAccom) {
		this.codeAccom = codeAccom;
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

	public String getCodeAccom() {
		return codeAccom;
	}

	public double getPrice() {
		return price;
	}
	
	public int getNum() {
		return num;
	}

	@Override
	public String serialize() {
		StringBuilder sb = new StringBuilder();
		sb.append(code).append("@").append(type).append("@").append(stars).append("@").append(name).append("@").append(codeAccom).append("@").append(codeAccom);
		return sb.toString();
	}
	
	

}
