package util.logic;

import java.util.Date;

public class LogicUtil {

	public static int getDays(Date dateStart, Date dateFinish) {
		if (dateStart != null && dateFinish != null) {
			long diff = Math.abs(dateFinish.getTime() - dateStart.getTime());
			long diffDays = diff / (24 * 60 * 60 * 1000);
			return (int) diffDays;
		}
		return 0;
	}

}
