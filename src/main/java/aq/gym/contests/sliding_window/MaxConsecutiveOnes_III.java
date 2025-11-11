package aq.gym.contests.sliding_window;

import java.util.stream.IntStream;

public class MaxConsecutiveOnes_III {

//	https://leetcode.com/problems/max-consecutive-ones-iii/description
	public static void main(String[] args) {
//		int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
//		int k = 2;
//		int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
//		int k = 3;
//		int[] nums = {0,0,0,1};
//		int k = 4;
		int[] nums = IntStream.generate(() -> (int) (Math.random() * 2)).limit(100_000).toArray();
		int k = nums.length;
		System.out.println(new MaxConsecutiveOnes_III().longestOnes(nums, k));
	}

    public int longestOnes(int[] nums, int k) {
    	int zeroCount = 0, longestOnesSequenceSize = 0;
        for(int i = 0, j = 0; i < nums.length; i++) {
        	if(nums[i] == 0) zeroCount++;
        	if(zeroCount > k) {
        		while(zeroCount > k) {
        			if(nums[j++] == 0) zeroCount--;
        		}
        	}
        	longestOnesSequenceSize = Math.max(longestOnesSequenceSize, i - j + 1);
        }
        return longestOnesSequenceSize;
    }
}
