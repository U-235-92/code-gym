package aq.gym.contests.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInStream {

//	https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
	public static void main(String[] args) {
//		int k = 3;
//		int[] nums = {4,5,8,2};
		int k = 3;
		int[] nums = {4,2};
		KthLargest kthLargest = new KthLargest(k, nums);
		add(kthLargest, 1);
		add(kthLargest, 1);
		add(kthLargest, -1);
		add(kthLargest, 3);
		add(kthLargest, 4);
	}
	
	private static void add(KthLargest kthLargest, int val) {
		System.out.println(kthLargest.add(val));
	}
}

class KthLargest {

	private int k;
	private PriorityQueue<Integer> pq;
	
    public KthLargest(int k, int[] nums) {
        this.k = k;
        initPQ(nums);
    }
    
    private void initPQ(int[] nums) {
    	if(nums.length == 0) {
    		pq = new PriorityQueue<>();
    	} else {    		
    		pq = new PriorityQueue<>();
    		if(k > nums.length) {
    			for(int i = 0; i < nums.length; i++) {
        			pq.offer(nums[i]);
        		}
    		} else {
    			Arrays.sort(nums);
    			for(int i = nums.length - k; i < nums.length; i++) {
    				pq.offer(nums[i]);
    			}    			
    		}
    	}
    }
    
    public int add(int val) {
    	int kth = pq.isEmpty() ? val : pq.peek();
        if(val <= kth) {
        	if(pq.size() < k) {        		
        		pq.offer(val);
        		kth = pq.peek();
        		return kth;
        	} else {
        		while(pq.size() >= k && (kth = pq.peek()) < val) {
            		pq.poll();
            	}
        		if(val >= pq.peek()) {        			
        			pq.offer(val);
        		}
            	kth = pq.peek();
            	return kth;
        	}
        } else {
        	while(pq.size() >= k && (kth = pq.peek()) < val) {
        		pq.poll();
        	}
        	pq.offer(val);
        	kth = pq.peek();
        	return kth;
        }
    }
}
