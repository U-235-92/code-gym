package aq.gym.contests.realization;

import java.util.PriorityQueue;

public class SmallestInfiniteSet {
	
	private PriorityQueue<Integer> numbersPQ;
	private int currentMinNumber = 1;
	
    public SmallestInfiniteSet() {
    	numbersPQ = new PriorityQueue<>();
    }
    
    public int popSmallest() {
    	if(!numbersPQ.isEmpty()) {
    		return numbersPQ.poll();
    	}
    	return currentMinNumber++;
    }
    
    public void addBack(int num) {
    	if(!numbersPQ.contains(num) && num < currentMinNumber) {
    		numbersPQ.offer(num);
    	}
    }
    
//	https://leetcode.com/problems/smallest-number-in-infinite-set/
	public static void main(String[] args) {
//		Test Case 1: Basic Pop Sequence
//		Expected Output: [1, 2, 3]
//		SmallestInfiniteSet obj = new SmallestInfiniteSet();
//		popSmallest(obj);
//		popSmallest(obj);
//		popSmallest(obj);
		
//		Test Case 2: Add Back After Pop
//		Expected Output: [1, 1, 2]
//		SmallestInfiniteSet obj = new SmallestInfiniteSet();
//		popSmallest(obj); // 1
//		addBack(obj, 1);  // Add 1 back
//		popSmallest(obj); // 1 (re-added)
//		popSmallest(obj); // 2
		
//		Test Case 3: Multiple Adds & Pops (Mixed Order)
//		Expected Output: [1, 2, 1, 3, 4]
//		SmallestInfiniteSet obj = new SmallestInfiniteSet();
//		popSmallest(obj); // 1
//		popSmallest(obj); // 2
//		addBack(obj, 3);  // Add 3
//		addBack(obj, 1);  // Add 1
//		popSmallest(obj); // 1 (smallest available)
//		popSmallest(obj); // 3 (next smallest)
//		popSmallest(obj); // 4
		
//		Test Case 4: Duplicate AddBack (No Effect)
//		Expected Output: [1, 1, 2]
		SmallestInfiniteSet obj = new SmallestInfiniteSet();
		popSmallest(obj); // 1
		addBack(obj, 1);  // Add 1
		addBack(obj, 1);  // Duplicate (ignored)
		popSmallest(obj); // 1
		popSmallest(obj); // 2
	}
	
	private static void popSmallest(SmallestInfiniteSet set) {
		System.out.print(set.popSmallest() + " ");
	}
	
	private static void addBack(SmallestInfiniteSet set, int num) {
		set.addBack(num);
	}
}
