package aq.gym.contests.array.prefix_sum;

public class RangeSumQueryImmutable {

//	https://leetcode.com/problems/range-sum-query-immutable/
	public static void main(String[] args) {
		int[] nums = {-2, 0, 3, -5, 2, -1};
		NumArray array = new NumArray(nums);
		System.out.println(array.sumRange(1, 4));
	}

	private static class NumArray {
		
		private int[] nums;
		
	    public NumArray(int[] nums) {
	    	this.nums = new int[nums.length];
	    	int prefixSum = 0;
	    	for(int i = 0; i < nums.length; i++) {
	    		prefixSum += nums[i];
	    		this.nums[i] = prefixSum;
	    	}
	    }
	    
	    public int sumRange(int left, int right) {
	        if(left == 0) {
	        	return nums[right];
	        } else {
	        	return nums[right] - nums[left - 1];
	        }
	    }
	}
}
