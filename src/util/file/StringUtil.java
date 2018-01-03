package util.file;

public class StringUtil {

	public static String formatSentece(String original) {
		if (!original.isEmpty()) {
			char firstLetter = original.toUpperCase().charAt(0);
			return firstLetter + original.substring(1, original.length());
		}
		return "";
	}
}
