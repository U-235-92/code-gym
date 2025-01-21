package aq.gym.thread.asynch.price_finder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		To test full load of CPU use follow code with yours limit CPU characteristic
//		List<Shop> shops = Stream.generate(() -> new Shop("Test")).limit(shopsLimitCPU).toList();
		List<Shop> shops = List.of(
				new Shop("BestPrice"), 
				new Shop("LetsSaveBig"),
				new Shop("MyFavoriteShop"),
				new Shop("BuyItAll"),
				new Shop("ShopEasy"));
//		getPricesStreamWay(shops);
//		getPricesParallelStreamWay(shops);
//		getPricesAsyncWay(shops);
//		getDiscountPricesStreamWay(shops);
//		getDiscountPricesAsyncWay(shops);
//		getRatedPrice(shops);
		getDiscountPricesAsyncAsStream(shops);
	}
	
	@SuppressWarnings("unused")
	private static void getPricesStreamWay(List<Shop> shops) {
		PriceService priceService = new PriceService(shops);
		long start = System.currentTimeMillis();
		System.out.println(priceService.findPricesNoAsynchWay("Thing"));
		long invocationTime = System.currentTimeMillis() - start;
		System.out.println("Total invocation time: " + invocationTime);
	}
	
	@SuppressWarnings("unused")
	private static void getPricesParallelStreamWay(List<Shop> shops) {
		PriceService priceService = new PriceService(shops);
		long start = System.currentTimeMillis();
		System.out.println(priceService.findPricesParallelStreamWay("Thing"));
		long invocationTime = System.currentTimeMillis() - start;
		System.out.println("Total invocation time: " + invocationTime);
	}

	@SuppressWarnings("unused")
	private static void getPricesAsyncWay(List<Shop> shops) {
		PriceService priceService = new PriceService(shops);
		long start = System.currentTimeMillis();
		System.out.println(priceService.findPricesAsynchWay("Thing"));
		long invocationTime = System.currentTimeMillis() - start;
		System.out.println("Total invocation time: " + invocationTime);
	}
	
	@SuppressWarnings("unused")
	private static void getDiscountPricesStreamWay(List<Shop> shops) {
		PriceService priceService = new PriceService(shops);
		long start = System.currentTimeMillis();
		System.out.println(priceService.findDiscountPricesStreamWay("Thing"));
		long invocationTime = System.currentTimeMillis() - start;
		System.out.println("Total invocation time: " + invocationTime);
	}
	
	@SuppressWarnings("unused")
	private static void getDiscountPricesAsyncWay(List<Shop> shops) {
		PriceService priceService = new PriceService(shops);
		long start = System.currentTimeMillis();
		System.out.println(priceService.findDiscountPricesAsynchWay("Thing"));
		long invocationTime = System.currentTimeMillis() - start;
		System.out.println("Total invocation time: " + invocationTime);
	}
	
	@SuppressWarnings("unused")
	private static void getRatedPrice(List<Shop> shops) throws InterruptedException, ExecutionException {
		PriceService priceService = new PriceService(shops);
		long start = System.currentTimeMillis();
		System.out.printf("%.2f%n", priceService.getRatedPrice("Thing"));
		long invocationTime = System.currentTimeMillis() - start;
		System.out.println("Total invocation time: " + invocationTime);
	}
	
	@SuppressWarnings("unused")
	private static void getDiscountPricesAsyncAsStream(List<Shop> shops) {
		PriceService priceService = new PriceService(shops);
		@SuppressWarnings("unchecked")
		CompletableFuture<String>[] completableFutures = priceService.findDiscountPricesStreamAsynchWay("Thing")
				.map(future -> future.thenAccept(System.out::println))
				.toArray(size -> new CompletableFuture[size]); 
		CompletableFuture.allOf(completableFutures).join();
	}
	
	@SuppressWarnings("unused")
	private static void simpleUsingAsync() throws InterruptedException, ExecutionException {
//		Example of simple using of asynch task
		Shop bestShop = new Shop("Best shop");
		long start = System.currentTimeMillis();
		Future<Double> futurePrice = bestShop.testGettingPriceAsynch("My best item");
		long invocationTime = System.currentTimeMillis() - start;
		System.out.println("Invocation time: " + invocationTime);
		double price = futurePrice.get();
		System.out.println("Price: " + price);
		System.out.println("Total time: " + (System.currentTimeMillis() - start));
	}
	
}
