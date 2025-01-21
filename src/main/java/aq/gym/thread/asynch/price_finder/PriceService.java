package aq.gym.thread.asynch.price_finder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceService {
	
	@NonNull
	private List<Shop> shops;
	
	public List<String> findPricesNoAsynchWay(String product) {
		return shops.stream()
				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
				.toList();
	}
	
	public List<String> findPricesParallelStreamWay(String product) {
		return shops.stream()
				.parallel()
				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
				.toList();
	}
	
	public List<String> findPricesAsynchWay(String product) {
		Executor executor = ExecutorHolder.getExecutor(shops.size());
		List<CompletableFuture<String>> futurePrices = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
				.collect(Collectors.toList());
		return futurePrices.stream()
				.map(CompletableFuture::join)
				.toList();
	}
	
	public List<String> findDiscountPricesStreamWay(String product) {
		return shops.stream()
				.map(shop -> shop.getDiscountPrice(product))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.toList();
	}
	
	public List<String> findDiscountPricesAsynchWay(String product) {
		Executor executor = ExecutorHolder.getExecutor(shops.size());
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getDiscountPrice(product), executor))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(quote -> CompletableFuture
						.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
				.collect(Collectors.toList());
		return priceFutures.stream()
				.map(CompletableFuture::join)
				.toList();
	}
	
	public Stream<CompletableFuture<String>> findDiscountPricesStreamAsynchWay(String product) {
		Executor executor = ExecutorHolder.getExecutor(shops.size());
		return shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getDiscountPrice(product), executor))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(quote -> CompletableFuture
						.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
	}
	
	public double getRatedPrice(String product) throws InterruptedException, ExecutionException {
		Future<Double> futurePrice = CompletableFuture.supplyAsync(() -> shops.get(0).getPrice(product))
				.thenCombine(CompletableFuture
						.supplyAsync(ExchangeService::getRate)
						.completeOnTimeout(1.0, 2, TimeUnit.SECONDS), (price, rate) -> price * rate)
				.orTimeout(3, TimeUnit.SECONDS);
		return futurePrice.get();
	}
}
