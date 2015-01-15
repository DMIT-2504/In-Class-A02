package ca.nait.demoApp;

public class Str {
	public static boolean isEmptyString(String str) {
		return !(str != null && str.trim().length() > 0);
	}
	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);
	}
}
