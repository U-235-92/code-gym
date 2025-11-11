package aq.gym.contests.sliding_window;

import java.util.stream.IntStream;

public class LongestSubarrayOf1sAfterDeletingOneElement {

//	https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description
	public static void main(String[] args) {
//		int[] nums = {1,1,0,1};
//		int[] nums = {0,1,1,1,0,1,1,0,0,1,1};
		int[] nums = IntStream.generate(() -> (int) (Math.random() * 2)).limit(100_000).toArray();
		System.out.println(new LongestSubarrayOf1sAfterDeletingOneElement().longestSubarray(nums));
	}

    public int longestSubarray(int[] nums) {
    	int i = 0, j = 0;
    	int zeroCount = 0, maxWindowSize = 0;
    	for(i = 0; i < nums.length; i++) {
    		int num = nums[i];
    		if(num == 0) {
    			zeroCount++;
    		}
    		if(zeroCount == 2) {
    			while(zeroCount == 2) {
    				if(nums[j++] == 0) zeroCount--;
    			}
    		}
    		maxWindowSize = Math.max(maxWindowSize, i - j + 1);
    	}
    	return maxWindowSize - 1;
    }
}
