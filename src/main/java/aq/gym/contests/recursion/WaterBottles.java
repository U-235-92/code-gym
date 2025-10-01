package aq.gym.contests.recursion;

public class WaterBottles {

//	https://leetcode.com/problems/water-bottles/description/
	public static void main(String[] args) {
		int numBottles = 100;
		int numExchange = 100;
		System.out.println(new WaterBottles().numWaterBottles(numBottles, numExchange));
	}

    public int numWaterBottles(int numBottles, int numExchange) {
        return numWaterBottles(numBottles, 0, numExchange, 0);
    }
    
    private int numWaterBottles(int fullNumBottles, int emptyNumBottles, int exchange, int total) {
    	if(fullNumBottles == 0) {
    		fullNumBottles = emptyNumBottles / exchange;
    		emptyNumBottles = emptyNumBottles % exchange;
    		total += fullNumBottles;
    	} else if(emptyNumBottles == 0) {
    		total += fullNumBottles; 
    		emptyNumBottles = fullNumBottles;
    		fullNumBottles = 0;
    	}
    	if(emptyNumBottles < exchange) {
    		if(fullNumBottles + emptyNumBottles < exchange) {    			
    			return total;
    		} else {
    			emptyNumBottles += fullNumBottles;
    			fullNumBottles = 0;
    		}
    	}
		return numWaterBottles(fullNumBottles, emptyNumBottles, exchange, total);
    }
}
