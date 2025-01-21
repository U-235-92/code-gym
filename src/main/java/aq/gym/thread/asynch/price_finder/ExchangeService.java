package aq.gym.thread.asynch.price_finder;

public class ExchangeService {

	public static double getRate() {
		delay();
		return (1 + (Math.random() * 10)) / (1 + (Math.random() / 10));
	}
	
	private static void delay() {
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
