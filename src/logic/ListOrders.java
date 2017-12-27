package logic;

import java.util.Date;
import java.util.List;

import fileUtil.IncorrectOrderException;
import internationalization.Internationalization;
import parser.ParserOrder;

public class ListOrders {

	private List<Order> orders;

	public static Order search(Date date, String dni) throws IncorrectOrderException {
		String nameFile = dni + "-" + Internationalization.getFormatDate(date);
		return new ParserOrder().parser(nameFile);
	}
}
