package aq.gym.thread.asynch.price_finder;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Shop {

	@Getter
	private final String name;
	
	public Future<Double> testGettingPriceAsynch(String product) {
//		Programming way of making future task
		CompletableFuture<Double> futurePrice = new CompletableFuture<Double>();
		Thread thread = new Thread(() -> {
			try {				
				double price = calculatePrice(product);
				futurePrice.complete(price);
			} catch(Exception ex) {
				futurePrice.completeExceptionally(ex);
			}
		});
		thread.start();
		return futurePrice;
//		Factory way of making future task
//		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}
	
	public String getDiscountPrice(String product) {
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
		return name + ":" + price + ":" + code;
	}
	
	public double getPrice(String product) {
		return calculatePrice(product);
	}

	private double calculatePrice(String product) { 
		delay();
		return Math.random() * product.charAt(0) + product.charAt(1);
	}
	
	private void delay() {
		try {
			Random random = new Random();
			int delay = 500 + random.nextInt(2000);
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
