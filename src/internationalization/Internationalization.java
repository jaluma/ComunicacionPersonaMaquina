package internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
	private static final String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage();
	private static final String DEFAULT_COUNTRY = Locale.getDefault().getCountry();

	private static String language = DEFAULT_LANGUAGE;
	private static String country = DEFAULT_COUNTRY;
	private static Locale locale = new Locale(language, country);
	private static ResourceBundle rb = ResourceBundle.getBundle("/res/values", locale);;

	public static String getString(String title) {
		return rb.getString(title);
	}
	
	public static Locale getLocate() {
		return locale;
	}
	
}
