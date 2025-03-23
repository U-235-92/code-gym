package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BillboardAdvertisement {

	public static void main(String[] args) {
		stressTest();
	}

	private static void getProfit() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<PayOffer> offers = new ArrayList<>();
			List<Integer> billboardWeeks = new ArrayList<>();
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int billboardsNumber = data[0];
			int advertisementNumber = data[1];
			int weeksNumber = data[2];
			for(int i = 0; i < billboardsNumber; i++) {
				billboardWeeks.add(weeksNumber);
			}
			for(int i = 0; i < advertisementNumber; i++) {
				data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				PayOffer offer = new PayOffer(data[0], data[1]);
				offers.add(offer);
			}
			Collections.sort(offers, Comparator.comparing(PayOffer::getOffer).reversed());
			long maximalProfit = getMaximalProfitGreedy(offers, billboardWeeks);
			System.out.println(maximalProfit);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static long getMaximalProfitGreedy(List<PayOffer> offers, List<Integer> billboardWeeks) {
		long maximalProfit = 0;
		while(offers.size() > 0 && billboardWeeks.size() > 0) {
			int maxProfitOfferIdx = 0, billboardWeeksIdx = 0;
			long bestOfferPrice = offers.get(maxProfitOfferIdx).getOffer();
			int offerNumberOfWeeks = offers.get(maxProfitOfferIdx).getNumberOfWeeks();
			int billboardFreeWeeks = billboardWeeks.get(billboardWeeksIdx);
			if(billboardFreeWeeks >= offerNumberOfWeeks) {				
				maximalProfit += bestOfferPrice;
				billboardFreeWeeks = billboardFreeWeeks - offerNumberOfWeeks;
				if(billboardFreeWeeks > 0) {
					billboardWeeks.add(billboardFreeWeeks);
				} 
				billboardWeeks.remove(billboardWeeksIdx);
			}
			offers.remove(maxProfitOfferIdx);
		}
		return maximalProfit;
	}
	
	private static long getMaximalProfitNaive(List<PayOffer> offers, List<Integer> billboardWeeks) {
		long profit = Long.MIN_VALUE;
		int totalWeeks = billboardWeeks.stream().reduce(0, Integer::sum);
		for(int i = 0; i < offers.size(); i++) {
			PayOffer iOffer = offers.get(i);
			int weeks = iOffer.getNumberOfWeeks();
			long currentProfit = iOffer.getOffer();
			for(int j = i; j < offers.size(); j++) {
				PayOffer jOffer = offers.get(j);
				if(iOffer != jOffer && weeks + jOffer.getNumberOfWeeks() <= totalWeeks) {
					weeks += jOffer.getNumberOfWeeks();
					currentProfit += jOffer.getOffer();
				}
			}
			if(currentProfit > profit) {
				profit = currentProfit;
			}
		}
		return profit;
	}
	
	private static void naiveTest() {
		int n = 2; //1 <= n <= 10^3
		int k = 4; //1 <= k <= 10^5
		int w = 4; //1 <= w <= 10^2
		List<PayOffer> offers = new ArrayList<>(List.of(
				PayOffer.of(88, 3), 
				PayOffer.of(72, 3), 
				PayOffer.of(32, 2),
				PayOffer.of(93, 2)));
		List<Integer> weeks = new ArrayList<>(List.of(4, 4));
		long naiveProfit = getMaximalProfitNaive(offers, weeks);
		System.out.println(naiveProfit);
	}
	
	private static void greedyTest() {
		int n = 2; //1 <= n <= 10^3
		int k = 4; //1 <= k <= 10^5
		int w = 4; //1 <= w <= 10^2
		List<PayOffer> offers = new ArrayList<>(List.of(
				PayOffer.of(88, 3), 
				PayOffer.of(72, 3), 
				PayOffer.of(32, 2),
				PayOffer.of(93, 2)));
		List<Integer> weeks = new ArrayList<>(List.of(4, 4));
		Collections.sort(offers, Comparator.comparing(PayOffer::getWeekCost).reversed());
		long greedyProfit = getMaximalProfitGreedy(offers, weeks);
		System.out.println(greedyProfit);
	}
	
	private static void stressTest() {
		while(true) {
			List<PayOffer> offers = new ArrayList<>();
			List<Integer> weeks = new ArrayList<>();
			List<PayOffer> offersCopy = new ArrayList<>();
			List<Integer> weeksCopy = new ArrayList<>();
			int n = (int) ((Math.random() * (5 - 1)) + 1); //1 <= n <= 10^3
			int k = (int) ((Math.random() * (5 - 1)) + 1); //1 <= k <= 10^5
			int w = (int) ((Math.random() * (5 - 1)) + 1); //1 <= w <= 10^2
			for(int i = 0; i < n; i++) {
				weeks.add(w);
				weeksCopy.add(w);
			}
			for(int i = 0; i < k; i++) {
				int cost = (int) ((Math.random() * 100) + 1); //1 <= cost <= 10^2
				int week = (int) ((Math.random() * (w - 1)) + 1); //1 <= week <= w
				PayOffer offer = new PayOffer(cost, week);
				offers.add(offer);
				offersCopy.add(offer);
			}
			Collections.sort(offersCopy, Comparator.comparing(PayOffer::getWeekCost).reversed());
			long naiveProfit = getMaximalProfitNaive(offers, weeks);
			long greedyProfit = getMaximalProfitGreedy(offersCopy, weeksCopy);
			if(naiveProfit == greedyProfit) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				System.out.println("Naive: " + naiveProfit + " Greedy: " + greedyProfit);
				System.out.println("n = " + n + ", k = " + k + ", w = " + w);
				System.out.println("Offers: " + offers);
				System.out.println("Weeks: " + weeks);
				return;
			}
		}
	}
	
	private static class PayOffer {
		
		private int costPerWeek;
		private int numberOfWeeks;
		
		public PayOffer(int costPerWeek, int numberOfWeeks) {
			super();
			this.costPerWeek = costPerWeek;
			this.numberOfWeeks = numberOfWeeks;
		}

		private static PayOffer of(int costPerWeek, int numberOfWeeks) {
			return new PayOffer(costPerWeek, numberOfWeeks);
		}
		
		private int getCostPerWeek() {
			return costPerWeek;
		}
		
		private int getNumberOfWeeks() {
			return numberOfWeeks;
		}

		private double getWeekCost() {
			return (double) (costPerWeek / numberOfWeeks);
		}
		
		private long getOffer() {
			return costPerWeek * numberOfWeeks;
		}
		
		@Override
		public String toString() {
			return "PayOffer [costPerWeek=" + costPerWeek + ", numberOfWeeks=" + numberOfWeeks + "]";
		}
	}
}
