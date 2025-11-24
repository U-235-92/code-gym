package aq.gym.contests.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class KthLargestElementInArray {

//	https://leetcode.com/problems/kth-largest-element-in-an-array/
	public static void main(String[] args) {
//		int[] nums = {3,2,1,5,6,4};
//		int k = 2;
//		int[] nums = {3,2,3,1,2,4,5,5,6};
//		int k = 4;
		int[] nums = IntStream.generate(() -> -10000 + (int) (Math.random() * 20000 + 1)).limit(100000).toArray();
		int k = 555;
		System.out.println(new KthLargestElementInArray().findKthLargest(nums, k));
	}

    public int findKthLargest(int[] nums, int k) {
    	int largestNumber = 0;
        PriorityQueue<Integer> numsPQ = new PriorityQueue<Integer>(Comparator.reverseOrder());
        for(int num : nums) {
        	numsPQ.offer(num);
        }
        while(k-- > 0) {
        	if(k == 0) {
        		largestNumber = numsPQ.poll();
        	} else {
        		numsPQ.poll();
        	}
        }
        return largestNumber;
    }
}
