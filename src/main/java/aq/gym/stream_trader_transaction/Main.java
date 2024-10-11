package aq.gym.stream_trader_transaction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Transaction> transactions = List.of(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));
		System.out.println(getTransactionsByDateThenSortedByDateAsc(transactions, 2011));
		System.out.println(getDistinctCities(transactions));
		System.out.println(getTrdersByCity(transactions, "Cambridge"));
		System.out.println(hasTraderInCity(transactions, "Milan"));
		System.out.println(getTotalAmountOfTransactionOfTradersFromCity(transactions, "Cambridge"));
		System.out.println(getMaxTransactionAmount(transactions));
		System.out.println(getTransactionMinAmount(transactions));
	}
	
	private static List<Transaction> getTransactionsByDateThenSortedByDateAsc(List<Transaction> transactions, int year) {
		return transactions
				.stream()
				.filter(transaction -> transaction.getYear() == year)
				.sorted(Comparator.comparing(Transaction::getYear))
				.collect(Collectors.toList());
	}
	
	private static List<String> getDistinctCities(List<Transaction> transactions) {
		return transactions
				.stream()
				.map(transaction -> transaction.getTrader().getCity())
				.distinct()
				.collect(Collectors.toList());
	}
	
	private static List<String> getTrdersByCity(List<Transaction> transactions, String city) {
		return transactions
				.stream()
				.filter(transaction -> transaction.getTrader().getCity().equals(city))
				.map(transaction -> transaction.getTrader().getName())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}
	
	private static boolean hasTraderInCity(List<Transaction> transactions, String city) {
		return transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals(city));
	}
	
	private static Integer getTotalAmountOfTransactionOfTradersFromCity(List<Transaction> transactions, String city) {
		return transactions
				.stream()
				.filter(transaction -> transaction.getTrader().getCity().equals(city))
				.map(Transaction::getValue)
				.reduce(0, Integer::sum);
	}
	
	private static Integer getMaxTransactionAmount(List<Transaction> transactions) {
		return transactions
				.stream()
				.map(Transaction::getValue)
				.max(Comparator.comparing(Integer::intValue))
				.get();
				
	}
	
	private static Transaction getTransactionMinAmount(List<Transaction> transactions) {
		return transactions
				.stream()
				.min(Comparator.comparing(Transaction::getValue))
				.get();
	}

}
