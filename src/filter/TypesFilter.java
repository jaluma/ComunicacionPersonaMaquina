package filter;

public enum TypesFilter {

	//Acoom
	TYPE("type", String.class),
	STARS("stars", int.class),
	PRICE_ACCOM("price_accom", double.class),
	//Park
	SALE("sale", boolean.class),
	COUNTRY("country", String.class),
	CITY("city", String.class),
	NAME("name", String.class);
	
	private String name;
	private Class<?> typeClass;
	
	private TypesFilter(String name, Class<?> classType) {
		this.name = name;
		this.typeClass = classType;
	}
	
	public String toString() {
		return name;
	}
	
	public Class<?> getClassType() {
		return typeClass;
	}
 	
}
