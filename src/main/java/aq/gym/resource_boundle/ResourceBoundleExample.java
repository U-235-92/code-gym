package aq.gym.resource_boundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBoundleExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale locale = Locale.forLanguageTag("en-US");
		String path = "aq/gym/resource_boundle/hello";
		printResourceBoundle(locale, path);
	}

	private static void printResourceBoundle(Locale locale, String path) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(path, locale);
		String msg = resourceBundle.getString("abra");
		System.out.println(msg);
	}
}
