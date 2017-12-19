package internationalization;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Internationalization {
	private static final String BUNDLE_NAME = "resources.values";
	
	private static final String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage();
	private static final String DEFAULT_COUNTRY = Locale.getDefault().getCountry();

	public static String language = DEFAULT_LANGUAGE;
	public static String country = DEFAULT_COUNTRY;;
	public static Locale locale = new Locale(language, country);
	
	private static ResourceBundle RESOURCE_BUNDLE = inicialiceBundle();

	private Internationalization() {
	}
	
	private static ResourceBundle inicialiceBundle() {
		ResourceBundle rb;
		try {
		rb = ResourceBundle.getBundle(BUNDLE_NAME, locale);
		} catch(MissingResourceException e) {
			rb = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return rb;
	}
	
	public static void changeLocation(String language, String country) {
		locale = new Locale(language, country);
		RESOURCE_BUNDLE = inicialiceBundle();
		ResourceBundle.clearCache();
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static Locale getLocate() {
		return locale;
	}
	
	public static String getActualDate() {
		Date now = new Date();
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
	    return formatter.format(now);
	}
	
	public static String getFormatDate(Date date) {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
	    return formatter.format(date);
	}
	
	public static String getCurrency(double amount) {
		return NumberFormat.getCurrencyInstance(locale).format(amount);
	}
}
