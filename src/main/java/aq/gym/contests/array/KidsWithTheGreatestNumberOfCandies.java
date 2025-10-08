package aq.gym.contests.array;

import java.util.ArrayList;
import java.util.List;

public class KidsWithTheGreatestNumberOfCandies {

//	https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description
	public static void main(String[] args) {
		int[] candies = {1};
		int extraCandies = 10;
		System.out.println(new KidsWithTheGreatestNumberOfCandies().kidsWithCandies(candies, extraCandies));
	}

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = -1;
        for(int amount : candies) {
        	if(amount > max) {
        		max = amount;
        	}
        }
        List<Boolean> result = new ArrayList<>();
        for(int i = 0; i < candies.length; i++) {
        	if(candies[i] + extraCandies >= max) {
        		result.add(true);
        	} else {
        		result.add(false);
        	}
        }
        return result;
    }
}
