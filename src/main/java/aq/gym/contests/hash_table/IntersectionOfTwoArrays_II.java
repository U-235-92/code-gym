package aq.gym.contests.hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArrays_II {

//	https://leetcode.com/problems/intersection-of-two-arrays-ii/
	public static void main(String[] args) {
		int[] nums1 = {9,4,9,8,4}, nums2 = {4,9,5};
		int[] intersection = new IntersectionOfTwoArrays_II().intersect(nums1, nums2);
		System.out.println(Arrays.toString(intersection));
	}
	
    public int[] intersect(int[] nums1, int[] nums2) {
       List<Integer> intersection = new ArrayList<>();
       Map<Integer, Integer> nums1Map = new HashMap<>();
       for(int i = 0; i < nums1.length; i++) {
    	   nums1Map.put(nums1[i], nums1Map.getOrDefault(nums1[i], 1) + 1);
       }
       for(int i = 0; i < nums2.length; i++) {
    	   int num2 = nums2[i];
    	   if(nums1Map.containsKey(num2) && nums1Map.get(num2) > 1) {
    		   intersection.add(nums2[i]);
    		   nums1Map.put(num2, nums1Map.get(num2) - 1);
    	   }
       }
       return intersection.stream().mapToInt(Integer::intValue).toArray();
    }
}
