package aq.gym.io.number_file_reader;

import java.text.NumberFormat;
import java.util.Locale;

public class MainApp {

	public static void main(String[] args) {
		String path = "src/main/java/aq/gym/io/number_file_reader/numbers.txt";
		NumberFileReader nfr = new NumberFileReader();
		System.out.println(nfr.readNumbersFrom(path));
		Locale us = new Locale("en", "US");
		Locale germany = new Locale("de", "DE");
		Locale russia = new Locale("ru", "RU");
		double avg = nfr.averageNumbersFrom(path);
		NumberFormat usNumberFormat = NumberFormat.getInstance(us);
		NumberFormat germanyNumberFormat = NumberFormat.getInstance(germany);
		NumberFormat russiaNumberFormat = NumberFormat.getInstance(russia);
		System.out.println(us.getCountry() +  " number: " +  usNumberFormat.format(avg));
		System.out.println(germany.getCountry() + " number: " + germanyNumberFormat.format(avg));
		System.out.println(russia.getCountry() + " number: " + russiaNumberFormat.format(avg));
	}

}
