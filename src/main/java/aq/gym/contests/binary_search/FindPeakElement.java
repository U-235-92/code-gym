package aq.gym.contests.binary_search;

public class FindPeakElement {

//	https://leetcode.com/problems/find-peak-element/
	public static void main(String[] args) {
//		int[] nums = {1,2,3,1}; // 2
//		int[] nums = {1,2,1,3,5,6,4}; // 5
//		int[] nums = {1,3,2}; // 1
//		int[] nums = {3,1,2}; // 0 or 2
//		int[] nums = {3,2,1}; // 0
//		int[] nums = {1,2}; // 1
//		int[] nums = {2,1}; // 0
		int[] nums = {3,4,3,2,1}; // 1
		System.out.println(new FindPeakElement().findPeakElement(nums));
	}

    public int findPeakElement(int[] nums) {
    	if(nums.length == 1) {
    		return 0;
    	} else if(nums.length == 2) {
    		return (Integer.compare(nums[0], nums[1]) == 1) ? 0 : 1;
    	} else {    		
    		return findPeakElement(nums, 0, nums.length - 1);
    	}
    }
    
    private int findPeakElement(int[] nums, int left, int right) {
    	int mid = (left + right) / 2;
    	if(mid == 0) {
    		if(nums[mid] > nums[mid + 1]) {
    			return mid;
    		} else {
    			return mid + 1;
    		}
    	} else if(mid == nums.length - 1) { 
    		if(nums[mid] > nums[mid - 1]) {    			
    			return mid;
    		} else {
    			return mid - 1;
    		}
    	} else {
    		if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
    			return mid;
    		} else if(nums[mid] < nums[mid + 1]) {
    			left = mid + 1;
    		} else if(nums[mid] < nums[mid - 1]) {
    			right = mid - 1;
    		}
    	}
    	return findPeakElement(nums, left, right);
    }
}
