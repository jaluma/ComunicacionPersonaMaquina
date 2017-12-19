package park;

public class Ticket {
	
	private String codeTicket;
	private String codePark;
	private double priceAdult;
	private double priceChild;
	
	public Ticket(String codeTicket, String codePark, double priceAdult, double priceChild) {
		setCodeTicket(codeTicket);
		setCodePark(codePark);
		setPriceAdult(priceAdult);
		setPriceChild(priceChild);
	}

	public String getCodeTicket() {
		return codeTicket;
	}

	public String getCodePark() {
		return codePark;
	}

	public double getPriceAdult() {
		return priceAdult;
	}

	public double getPriceChild() {
		return priceChild;
	}

	private void setCodeTicket(String codeTicket) {
		this.codeTicket = codeTicket;
	}

	private void setCodePark(String codePark) {
		this.codePark = codePark;
	}

	private void setPriceAdult(double priceAdult) {
		this.priceAdult = priceAdult;
	}

	private void setPriceChild(double priceChild) {
		this.priceChild = priceChild;
	}
	
	public String serialize() {
		StringBuilder sb = new StringBuilder();
		sb.append(codePark).append("@").append(codeTicket).append("@").append(priceAdult).append("@").append(priceChild);
		return sb.toString();
	}

}
