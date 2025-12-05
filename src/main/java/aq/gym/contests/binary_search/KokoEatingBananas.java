package aq.gym.contests.binary_search;

import java.util.Arrays;

public class KokoEatingBananas {

//	https://leetcode.com/problems/koko-eating-bananas
	public static void main(String[] args) {
		int[] piles = {30,11,23,4,20};
		int h = 11;
		System.out.println(new KokoEatingBananas().minEatingSpeed(piles, h));
	}

    public int minEatingSpeed(int[] piles, int h) {
    	if(piles.length == h) {
    		Arrays.sort(piles);
    		return piles[piles.length - 1];
    	} else {
    		Arrays.sort(piles);
    		int minSpeedEating = 1, maxSpeedEating = piles[piles.length - 1], answer = minSpeedEating;
    		while(minSpeedEating <= maxSpeedEating) {
    			long hours = 0;
    			int guessSpeedEating = minSpeedEating + (maxSpeedEating - minSpeedEating) / 2;
    			for(int i = 0; i < piles.length; i++) {
    				hours += Math.ceil((double) piles[i] / guessSpeedEating);
    				if(hours > h) {
    					break;
    				}
    			}
    			if(hours <= h) {
    				maxSpeedEating = guessSpeedEating - 1;
    				answer = guessSpeedEating;
    			} else {
    				minSpeedEating = guessSpeedEating + 1;
    			}
    		}
    		return answer;
    	}
    }
}
