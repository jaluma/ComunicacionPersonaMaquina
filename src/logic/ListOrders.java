package logic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import fileUtil.IncorrectOrderException;
import internationalization.Internationalization;
import parser.ParserOrder;

public class ListOrders {
	
	public static Order search(Date date, String dni) throws IncorrectOrderException, DataFormatException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (date != null) {
		String dateString = format.format(date);			//check format date
		
		String nameFile = dateString + "_" + dni + ".dat";
		return new ParserOrder().parser(nameFile);
		}
		else {
			throw new DataFormatException();
		}
	}
}
