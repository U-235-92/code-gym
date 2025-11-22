package aq.gym.contests.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumSubsequenceScore {

//	https://leetcode.com/problems/maximum-subsequence-score/
	public static void main(String[] args) {
		int[] nums1 = {1,3,3,2};
		int[] nums2 = {2,1,3,4};
		int k = 3;
		System.out.println(new MaximumSubsequenceScore().maxScore(nums1, nums2, k));
	}
	
    public long maxScore(int[] nums1, int[] nums2, int k) {
    	long maxScore = 0L, curSum = 0L;
        int[][] pairs = new int[nums1.length][2];
        PriorityQueue<Integer> nums1PQ = new PriorityQueue<>();
        for(int i = 0; i < nums1.length; i++) {
        	pairs[i][0] = nums1[i];
        	pairs[i][1] = nums2[i];
        }
        Arrays.sort(pairs, (arr1, arr2) -> Integer.compare(arr2[1], arr1[1]));
        for(int i = 0; i < pairs.length; i++) {
        	curSum += pairs[i][0];
        	nums1PQ.offer(pairs[i][0]);
        	if(nums1PQ.size() > k) {
        		curSum = curSum - nums1PQ.poll();
        	}
        	if(nums1PQ.size() == k) {
        		maxScore = Math.max(maxScore, curSum * pairs[i][1]);
        	}
        }
        return maxScore;
    }
}
