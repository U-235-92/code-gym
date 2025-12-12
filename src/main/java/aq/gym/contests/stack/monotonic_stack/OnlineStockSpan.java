package aq.gym.contests.stack.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class OnlineStockSpan {

//	https://leetcode.com/problems/online-stock-span/
	public static void main(String[] args) {
		StockSpanner spanner = new StockSpanner();
//		Case #1
//		next(spanner, 100);
//		next(spanner, 80);
//		next(spanner, 60);
//		next(spanner, 70);
//		next(spanner, 60);
//		next(spanner, 75);
//		next(spanner, 85);
		
//		Case #2
//		next(spanner, 7);
//		next(spanner, 2);
//		next(spanner, 1);
//		next(spanner, 2);
//		next(spanner, 2);
		
//		Case #3
//		next(spanner, 7);
//		next(spanner, 34);
//		next(spanner, 1);
//		next(spanner, 2);
//		next(spanner, 8);
		
//		Case #4
//		next(spanner, 8);
//		next(spanner, 5);
		
//		Case #5
		next(spanner, 100);
		next(spanner, 80);
		next(spanner, 60);
		next(spanner, 150);
		next(spanner, 60);
		
//		Case #6
//		next(spanner, 5);
//		next(spanner, 5);
	}

	private static void next(StockSpanner spanner, int price) {
		System.out.print(spanner.next(price) + " ");
	}
	
	private static class StockSpanner {

		private Deque<Stock> stocks;
		
	    public StockSpanner() {
	        stocks = new ArrayDeque<>();
	    }
	    
		public int next(int price) {
	    	if(stocks.isEmpty()) {
	    		Stock stock = new Stock(price, 1);
	    		stocks.push(stock);
	    		return 1;
	    	} else {
	    		if(price - stocks.peek().price < 0) {
	    			Stock stock = new Stock(price, 1);
		    		stocks.push(stock);
	    			return 1;
	    		} else {
	    			int span = 1;
	    			while(!stocks.isEmpty() && price >= stocks.peek().price) {
	    				Stock stock = stocks.poll();
	    				span += stock.span;
	    			}
	    			Stock stock = new Stock(price, span);
	    			stocks.push(stock);
	    			return span;
	    		}
	    	}
	    }
	}
	
	private static class Stock {
		
		private int price;
		private int span;
		
		Stock(int price, int span) {
			this.price = price;
			this.span = span;
		}
		
		@Override
		public String toString() {
			return String.format("%s, %s", price, span);
		}
	}
}
