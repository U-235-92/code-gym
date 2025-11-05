package aq.gym.contests.hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Find_X_SumOfAll_K_LongSubarrays_II {

//	https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/description/
	public static void main(String[] args) {
//		int[] nums = {1,1,2,2,3,4,2,3}; // 6,10,12
//		int k = 6, x = 2;
//		int[] nums = {3,8,7,8,7,5}; // 11,15,15,15,12
//		int k = 2, x = 2;
//		int[] nums = {1,2,2}; // 2,4
//		int k = 2, x = 1;
		int limit = (int) Math.pow(10, 5), min = 1, max = (int) Math.pow(10, 9);
		int[] nums = IntStream.generate(() -> min + (int) (Math.random() * (max - min) + 1)).limit(limit).toArray();
		int k = limit / 2, x = limit / 4;
		System.out.println(Arrays.toString(new Find_X_SumOfAll_K_LongSubarrays_II().findXSum(nums, k, x)));
	}

    @SuppressWarnings("unused")
	public long[] findXSum(int[] nums, int k, int x) {
    	long[] answer = new long[nums.length - k + 1];
    	
    	return answer;
    }
}
