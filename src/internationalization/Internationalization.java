package internationalization;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import product.ListProduct;

public class Internationalization {
	private static final String BUNDLE_NAME = "resources.values";

	private static final String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage();
	private static final String DEFAULT_COUNTRY = Locale.getDefault().getCountry();

	public static Locale locale = new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);

	private static ResourceBundle RESOURCE_BUNDLE = inicialiceBundle();

	public static final Locale[] LOCATION_SUPPORTED = new Locale[] { Locale.forLanguageTag("es-ES"),
			Locale.forLanguageTag("en-US") };

	private Internationalization() {
	}

	private static ResourceBundle inicialiceBundle() {
		ResourceBundle rb;
		Locale.setDefault(locale);
		try {
			rb = ResourceBundle.getBundle(BUNDLE_NAME, locale);
		} catch (MissingResourceException e) {
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

	public static int getIndexLanguageSelected() {
		return Arrays.asList(LOCATION_SUPPORTED).indexOf(locale);
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

	public static Date getDate(String formatDate) {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		try {
			return formatter.parse(formatDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrency(double amount) {
		return NumberFormat.getCurrencyInstance(locale).format(amount);
	}

	public static char getMnemonic(String key) {
		String str = key + "_m";
		return Internationalization.getString(str).charAt(0);
	}

	public static String getToolTips(String key) {
		String str = key + "_tt";
		return Internationalization.getString(str);
	}

	public static String getProduct(String code) {
		String str = code + "_name";
		String aux = Internationalization.getString(str);
		char letter = aux.charAt(0);
		return letter == '!' ? ListProduct.searchProduct(code).getName() : aux;
	}

	public static String getDescription(String code) {
		String str = code + "_description";
		String aux = Internationalization.getString(str);
		char letter = aux.charAt(0);
		return letter == '!' ? ListProduct.searchPark(code).getDescription() : aux;
	}

	public static String getCountry(String country) {
		return Internationalization.getString(country);
	}
}
