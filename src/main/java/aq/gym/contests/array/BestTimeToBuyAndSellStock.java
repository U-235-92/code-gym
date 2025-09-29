package aq.gym.contests.array;

public class BestTimeToBuyAndSellStock {

//	https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
	public static void main(String[] args) {
		int[] prices = {7,1,5,3,6,4}; // 5
//		int[] prices = {7,6,4,3,1}; // 0
//		int[] prices = {0,1,9,5,9}; // 9
//		int[] prices = {8}; // 0
//		int[] prices = {5,8}; // 3
//		int[] prices = {2,6,8}; // 6
//		int[] prices = {2,2}; // 0
		System.out.println(new BestTimeToBuyAndSellStock().maxProfit(prices));
	}

    public int maxProfit(int[] prices) {
    	return maxProfitByArrayOperations(prices);
    }
    
    private int maxProfitByArrayOperations(int[] prices) {
    	int minPrice = prices[0];
		int maxProfit = 0;
		for(int i = 1; i < prices.length; i++) {
			int curPrice = prices[i];
			int curProfit = curPrice - minPrice;
			if(curProfit > maxProfit) {
				maxProfit = curProfit;
			}
			if(curPrice < minPrice) {
				minPrice = curPrice;
			}
		}
		return maxProfit;
    }
    
	@SuppressWarnings("unused")
    private int maxProfitDynamicProgramming(int[] prices, int max, int previousMax, int buyDay) {
    	if(buyDay == prices.length) {
    		return max;
    	}
    	for(int sellDay = prices.length - 1; sellDay >= buyDay; sellDay--) {
    		int profit = prices[sellDay] - prices[buyDay];
    		max = Math.max(previousMax, profit);
    		previousMax = max;
    	}
    	return maxProfitDynamicProgramming(prices, max, previousMax, ++buyDay);
    }
}
