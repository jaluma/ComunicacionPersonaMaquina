package fileUtil;

public class AssertParam {

	public static void assertNoNegative(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("ERROR: Only permitive unsigned integers");
		}
	}

	public static void assertNoNullString(String str) {
		if (str == null) {
			throw new IllegalArgumentException("ERROR: Object.String equals NULL.");
		}
	}

	public static void assertIsEmptry(String str) {
		if (str.isEmpty())
			throw new IllegalArgumentException("ERROR: Object.String is emptry");
	}

}
