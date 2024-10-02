package aq.gym.console_dot_reader;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		final char DOT_SYMBOL = '.';
		final char SPACE_SYMBOL = ' ';
		int spaceAmount = 0;
		boolean isRun = true;
		System.out.println("Write a line.\nWrite \'.' for finish progrmm.");
		do {
			char readSymbol = (char) System.in.read();
			switch(readSymbol) {
			case DOT_SYMBOL:
				isRun = false;
				break;
			case SPACE_SYMBOL:
				spaceAmount++;
				break;
			}
		} while (isRun);
		System.out.println("The number of spaces: " + spaceAmount);
	}
}
