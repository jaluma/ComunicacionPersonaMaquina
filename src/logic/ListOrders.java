package logic;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import internationalization.Internationalization;

public class ListOrders {

	private List<Order> orders;
	
	public static Order search(Date date, String dni) {
		try {
			String nameFile = dni + "-" + Internationalization.getFormatDate(date);
			return new OrderParser(nameFile);
		} catch (IOException e) {
			return null;
		}
	}
}
