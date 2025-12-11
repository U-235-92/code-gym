package aq.gym.contests.dynamic;

public class BestTimeToBuyAndSellStockWithTransactionFee {

//	https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
	public static void main(String[] args) {
//		int[] prices = {1,3,2,8,4,9};
//		int fee = 2;
//		int[] prices = {1,3,7,5,10,3};
//		int fee = 3;
//		int[] prices = {1,3,8,5};
//		int fee = 2;
//		int[] prices = {1,5,8};
//		int fee = 1;
//		int[] prices = {9,8,7,1,2};
//		int fee = 3;
		int[] prices = {1,4,6,2,8,3,10,14};
		int fee = 3;
		System.out.println(new BestTimeToBuyAndSellStockWithTransactionFee().maxProfit(prices, fee));
	}

    public int maxProfit(int[] prices, int fee) {
        if(prices.length == 1) {
            return 0;
        }
        int[] hold = new int[prices.length]; 
        int[] cash = new int[prices.length]; 
        hold[0] = -prices[0];  
        cash[0] = 0;           
        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i] - fee);
        }
        return cash[prices.length - 1];
    }
}
