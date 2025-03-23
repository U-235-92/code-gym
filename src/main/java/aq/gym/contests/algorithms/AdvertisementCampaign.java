package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementCampaign {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			int n = Integer.valueOf(br.readLine());
			List<Long> costs = Arrays.stream(br.readLine().split("\\s"))
					.map(Long::valueOf)
					.sorted(Comparator.reverseOrder())
					.collect(Collectors.toCollection(ArrayList::new));
			List<Long> clicks = Arrays.stream(br.readLine().split("\\s"))
					.map(Long::valueOf)
					.sorted(Comparator.reverseOrder())
					.collect(Collectors.toCollection(ArrayList::new));
			long maximalRevenue = getMaximalRevenueGreedy(clicks, costs);
			System.out.println(maximalRevenue);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static long getMaximalRevenueGreedy(List<Long> clicks, List<Long> costs) {
		long revenue = 0;
		while(clicks.size() > 0 && costs.size() > 0) {
			int mostExpensiveOfferIdx = 0;
			long maxClickNumber = clicks.get(mostExpensiveOfferIdx);
			long maxCostAmount = costs.get(mostExpensiveOfferIdx);
			revenue += maxClickNumber * maxCostAmount;
			clicks.remove(mostExpensiveOfferIdx);
			costs.remove(mostExpensiveOfferIdx);
		}
		return revenue;
	}
}
