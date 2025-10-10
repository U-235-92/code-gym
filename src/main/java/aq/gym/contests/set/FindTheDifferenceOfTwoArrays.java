package aq.gym.contests.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindTheDifferenceOfTwoArrays {

//	https://leetcode.com/problems/find-the-difference-of-two-arrays/description
	public static void main(String[] args) {
		int[] nums1 = {1,2,5};
		int[] nums2 = {1,2,8};
		List<List<Integer>> result = new FindTheDifferenceOfTwoArrays().findDifference(nums1, nums2);
		result.forEach(System.out::print);
	}

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
    	List<List<Integer>> collector = new LinkedList<>();
    	Set<Integer> left = new HashSet<>();
    	Set<Integer> right = new HashSet<>();
    	int i = 0, j = 0;
    	while(i < nums1.length && j < nums2.length) {
    		left.add(nums1[i]);
    		right.add(nums2[j]);
    		i++; j++;
    	}
    	while(i < nums1.length) {
    		left.add(nums1[i]);
    		i++;
    	}
    	while(j < nums2.length) {
    		right.add(nums2[j]);
    		j++;
    	}
    	find(right, nums1, collector);
    	find(left, nums2, collector);
    	return collector;
    }
    
    private static void find(Set<Integer> set, int[] nums, List<List<Integer>> collector) {
    	Set<Integer> accumulator = new HashSet<>();
    	for(int num : nums) {
    		if(!set.contains(num)) {
    			accumulator.add(num);
    		}
    	}
    	collector.add(new ArrayList<>(accumulator));
    }
}
