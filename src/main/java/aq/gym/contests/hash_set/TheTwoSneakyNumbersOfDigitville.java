package aq.gym.contests.hash_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TheTwoSneakyNumbersOfDigitville {

//	https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/description/
	public static void main(String[] args) {
		int[] nums = {7,1,5,4,3,4,6,0,9,5,8,2};
		System.out.println(Arrays.toString(new TheTwoSneakyNumbersOfDigitville().getSneakyNumbers(nums)));
	}

    public int[] getSneakyNumbers(int[] nums) {
        Set<Integer> sneakyNumbersFilter = new HashSet<>();
        List<Integer> sneakyNumbers = new ArrayList<Integer>();
        for(int num : nums) {
        	if(sneakyNumbersFilter.add(num) == false) {
        		sneakyNumbers.add(num);
        	}
        }
        return sneakyNumbers.stream().mapToInt(Integer::valueOf).toArray();
    }
}
