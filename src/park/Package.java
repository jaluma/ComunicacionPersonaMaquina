package park;

import fileUtil.AssertParam;

public class Package extends Place {
	
	private Park park;
	private Accommodation accom;
	private int duration;
	private double priceAdult;
	private double priceChild;
	
	public Package(String code, String name, String codePark, String codeAccom, int duration, double priceAdult, double priceChild) {
		super(code, name);
		setPark(new Park(codePark));
		setAccom(new Accommodation(codeAccom));
		setDuration(duration);
		setPriceAdult(priceAdult);
		setPriceChild(priceChild);
	}

	public String getCode() {
		return code;
	}

	public Park getPark() {
		return park;
	}

	public Accommodation getAccom() {
		return accom;
	}

	public int getDuration() {
		return duration;
	}

	public double getPriceAdult() {
		return priceAdult;
	}

	public double getPriceChild() {
		return priceChild;
	}

	private void setPark(Park park) {
		this.park = park;
	}

	private void setAccom(Accommodation accom) {
		this.accom = accom;
	}

	private void setDuration(int duration) {
		AssertParam.assertNoNegative(duration);
		this.duration = duration;
	}

	private void setPriceAdult(double priceAdult) {
		AssertParam.assertNoNegative((int)priceAdult);
		this.priceAdult = priceAdult;
	}

	private void setPriceChild(double priceChild) {
		AssertParam.assertNoNegative((int)priceChild);
		this.priceChild = priceChild;
	}

	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
