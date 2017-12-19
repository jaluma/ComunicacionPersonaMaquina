package park;

public abstract class Place {
	
	protected String code;
	protected String name;
	
	public Place(String code, String name) {
		setCode(code);
		setName(name);
	}
	
	protected void setCode(String code) {
		this.code = code;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public abstract String serialize();

}
