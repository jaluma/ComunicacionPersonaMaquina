package product;

import internationalization.Internationalization;

public class Ticket extends Product {

	private String codeTicket;
	private double priceAdult;
	private double priceChild;

	public Ticket(String codeTicket, String codePark, double priceAdult, double priceChild) {
		super(codeTicket, ListProduct.searchPark(codePark).getName(), codePark);
		setCodeTicket(codeTicket);
		setPriceAdult(priceAdult);
		setPriceChild(priceChild);
	}

	public String getCodeTicket() {
		return codeTicket;
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

	private void setPriceAdult(double priceAdult) {
		this.priceAdult = priceAdult;
	}

	private void setPriceChild(double priceChild) {
		this.priceChild = priceChild;
	}

	public String toString() {
		String str = "";
		str += Internationalization.getString("ticket_subtitle") + ": " + code + " / " + name + "\n";
		str += Internationalization.getString("initial_date") + ": " + Internationalization.getFormatDate(date) + " / "
				+ Internationalization.getString("number_days") + ": " + duration + "\n";
		str += Internationalization.getString("number_adult") + ": " + numberAdult + " / "
				+ Internationalization.getString("number_child") + ": " + numberChild + "\n";
		return str;
	}

	@Override
	public double getTotal() {
		return numberAdult * priceAdult + numberChild * priceChild;
	}

	@Override
	public boolean isSale() {
		return park.isSale();
	}

}
