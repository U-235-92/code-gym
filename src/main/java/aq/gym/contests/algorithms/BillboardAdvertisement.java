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
		getProfit();
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
			Collections.sort(offers, Comparator.comparing(PayOffer::getCostPerWeek).reversed());
			long maximalProfit = getMaximalProfitGreedy(offers, billboardWeeks);
			System.out.println(maximalProfit);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static long getMaximalProfitGreedy(List<PayOffer> offers, List<Integer> billboardWeeks) {
		long maximalProfit = 0;
		int slotsRemain = billboardWeeks.size() * billboardWeeks.get(0);
		for(PayOffer offer : offers) {
			int bestOfferCost = offer.getCostPerWeek();
			int bestOfferNumberWeeks = offer.getNumberOfWeeks();
			int weekToUse = Integer.min(slotsRemain, bestOfferNumberWeeks);
			maximalProfit += (bestOfferCost * weekToUse);
			slotsRemain -= weekToUse;
			if(slotsRemain <= 0) {
				break;
			}
		}
		return maximalProfit;
	}
	
	@SuppressWarnings("unused")
	private static long getMaximalProfitNaive(List<PayOffer> offers, List<Integer> billboardWeeks) {
		long maximalProfit = Long.MIN_VALUE;
		int slotsRemain = billboardWeeks.size() * billboardWeeks.get(0);
		for(int i = 0; i < offers.size(); i++) {
			List<Integer> profits = new ArrayList<>();
			int offerWeekCost = offers.get(i).getCostPerWeek();
			int offerNumberWeeks = offers.get(i).getNumberOfWeeks();
			int weekToUse = Integer.min(slotsRemain, offerNumberWeeks);
			profits.add(offerWeekCost * weekToUse);
			slotsRemain -= weekToUse;
			if(slotsRemain > 0) {				
				for(int j = i; j < offers.size(); j++) {
					if(offers.get(i) != offers.get(j) && slotsRemain > 0) {
						offerWeekCost = offers.get(j).getCostPerWeek();
						offerNumberWeeks = offers.get(j).getNumberOfWeeks();
						weekToUse = Integer.min(slotsRemain, offerNumberWeeks);
						profits.add(offerWeekCost * weekToUse);
						slotsRemain -= weekToUse;
					}
				}
			} 
			slotsRemain = billboardWeeks.size() * billboardWeeks.get(0);
			long totalProfits = profits.stream().mapToLong(Long::valueOf).sum();
			if(totalProfits > maximalProfit) {
				maximalProfit = totalProfits;
			}
		}
		return maximalProfit;
	}

	private static class PayOffer {
		
		private int costPerWeek;
		private int numberOfWeeks;
		
		public PayOffer(int costPerWeek, int numberOfWeeks) {
			super();
			this.costPerWeek = costPerWeek;
			this.numberOfWeeks = numberOfWeeks;
		}

		private int getCostPerWeek() {
			return costPerWeek;
		}
		
		private int getNumberOfWeeks() {
			return numberOfWeeks;
		}
		
		@Override
		public String toString() {
			return "PayOffer [costPerWeek=" + costPerWeek + ", numberOfWeeks=" + numberOfWeeks + "]";
		}
	}
}
