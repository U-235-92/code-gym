package aq.gym.contests.array;

import java.util.HashSet;

public class ContainsDuplicateII {

//	https://leetcode.com/problems/contains-duplicate-ii/
	public static void main(String[] args) {
//		Case #1
//		int[] nums = {1,2,3,1}; // t
//		int k = 3;
		
//		Case #2
//		int[] nums = {1,0,1,1}; // t
//		int k = 1;
		
//		Case #3
//		int[] nums = {1,2,3,1,2,3}; // f
//		int k = 2;
		
//		Case #4
//		int[] nums = {1,4,2,3,1,2}; // t
//		int k = 3;
		
//		Case #5
//		int[] nums = IntStream.generate(() -> (int) (Math.random() * Math.pow(10, 9))).limit((int) Math.pow(10, 5)).toArray();
//		int k = (int) Math.pow(10, 5); 
//		int k = 5;
		
//		Case #6
//		int[] nums = {1,4,2,3,1,2}; // t
//		int k = 3;
		
//		Case #7
//		int[] nums = {4,1,2,3,1,5}; // t
//		int k = 3;
		
//		Case #8
		int[] nums = {1,2,3,4,5,6,7,8,9,9}; // t
		int k = 3;
		System.out.println(new ContainsDuplicateII().containsNearbyDuplicate(nums, k));
	}

	public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
