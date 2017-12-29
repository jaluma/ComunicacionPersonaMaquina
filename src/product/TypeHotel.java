package product;

public enum TypeHotel {
	HO("Hotel"), AH("ApartHotel"), AP("Apartment");

	private String type;

	TypeHotel(String type) {
		this.type = type;
	}

	public String toString() {
		return this.type;
	}

	// public static TypeHotel getType(String type) {
	// switch (type) {
	// case "Hotel":
	// return TypeHotel.HO;
	// case "ApartHotel":
	// return TypeHotel.AH;
	// case "Apartment":
	// return TypeHotel.AP;
	// default:
	// throw new IllegalArgumentException("ERROR: Type incorrect");
	// }
	// }
}
