package aq.gym.console.slow_echo_printer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SlowPrinter {

//	just for fun
	public static void main(String[] args) throws IOException {
		while(true) {
			char ch = (char) System.in.read();
			if(ch == '.') {
				break;
			} else {
				System.out.print(ch);
				sleep(100);
			}
		}
	}
	
	private static void sleep(int mills) {
		try {
			TimeUnit.MILLISECONDS.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
