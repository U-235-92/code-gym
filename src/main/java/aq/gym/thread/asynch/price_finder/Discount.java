package aq.gym.thread.asynch.price_finder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Discount {

	public static String applyDiscount(Quote quote) {
		return String.format("%s price is %.2f", 
				quote.getShopName(), Discount.apply(quote.getPrice(), quote.getDiscountCode()));
	}
	
	private static double apply(double price, Code code) {
		delay();
		return price * (100 - code.percentage) / 100;
	}
	
	private static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequiredArgsConstructor
	enum Code {
		
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		@Getter
		private final int percentage;
	}
}
