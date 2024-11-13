package aq.gym.console.char_converter;

import java.io.IOException;

public class ConsoleCharConvertor {

	public static void main(String[] args) throws IOException {
		final char DOT_SYMBOL = '.';
		final int SHIFT = 32;
		final int MIN_UPPER_CASE_CHAR_CODE = 65;
		final int MAX_UPPER_CASE_CHAR_CODE = 90;
		final int MIN_LOWER_CASE_CHAR_CODE = 97;
		final int MAX_LOWER_CASE_CHAR_CODE = 122;
		boolean isRun = true;
		while (isRun) {
			char readChar = (char) System.in.read();
			if (readChar == DOT_SYMBOL) {
				isRun = false;
			} else {
				if(readChar >= MIN_UPPER_CASE_CHAR_CODE && readChar <= MAX_UPPER_CASE_CHAR_CODE) {
					readChar += SHIFT;
				} else if(readChar >= MIN_LOWER_CASE_CHAR_CODE && readChar <= MAX_LOWER_CASE_CHAR_CODE) {
					readChar -= SHIFT;
				}
				System.out.print(readChar);
			}
		}
	}
}
