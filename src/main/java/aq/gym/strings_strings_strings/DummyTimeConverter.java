package aq.gym.strings_strings_strings;

public class DummyTimeConverter {

	public static void main(String[] args) {
		System.out.println("After convert: " + convert("10:30 PM"));
	}

	private static String convert(String time) {
		if(!time.matches("\\b([0][0-9]|[1][0-2]):[0-5][0-9]\\s[AP]M\\b")) {
			throw new IllegalArgumentException();
		}
		int hour = Integer.valueOf(time.split(":", 2)[0]);
		String result = "";
		if(time.split("\\s")[1].equals("PM")) {
			hour += 12;
			result = hour + ":" + time.split(":")[1].split("\\s")[0];
		} else {
			result = time.split("\\s")[0];
		}
		return result;
	}
}
