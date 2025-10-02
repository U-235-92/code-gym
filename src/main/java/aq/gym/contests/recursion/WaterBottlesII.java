package aq.gym.contests.recursion;

public class WaterBottlesII {

	public static void main(String[] args) {
		int numBottles = 2;
		int numExchange = 1;
		System.out.println(new WaterBottlesII().maxBottlesDrunk(numBottles, numExchange));
	}

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        return maxBottlesDrunk(numBottles, 0, numExchange, 0);
    }
    
    private int maxBottlesDrunk(int fullBottles, int emptyBottles, int numExchange, int total) {
    	if(fullBottles == 0) {
    		while(emptyBottles >= numExchange) {
    			emptyBottles -= numExchange;
    			fullBottles++;
    			numExchange++;
    		}
    		total += fullBottles;
    	} else if(emptyBottles == 0) {
    		total += fullBottles; 
    		emptyBottles = fullBottles;
    		fullBottles = 0;
    		while(emptyBottles >= numExchange) {
    			emptyBottles -= numExchange;
    			fullBottles++;
    			numExchange++;
    		}
    		total += fullBottles;
    	}
    	if(emptyBottles + fullBottles < numExchange) {
    		return total;
    	} else if(emptyBottles + fullBottles == numExchange) {
    		emptyBottles = emptyBottles + fullBottles;
    		fullBottles = 1;
    		return total + fullBottles;
    	} else {
    		emptyBottles = emptyBottles + fullBottles;
    		fullBottles = 0;
    	}
    	return maxBottlesDrunk(fullBottles, emptyBottles, numExchange, total);
    }
}
