package aq.gym.contests.sliding_window;

public class MaximumAverageSubarray_I {

//	https://leetcode.com/problems/maximum-average-subarray-i
	public static void main(String[] args) {
		int[] nums = {1,12,-5,-6,50,3};
		int k = 4;
//		int[] nums = {5};
//		int k = 1;
//		int[] nums = {8860,-853,6534,4477,-4589,8646,-6155,-5577,-1656,-5779,-2619,-8604,-1358,-8009,4983,7063,3104,-1560,4080,2763,5616,-2375,2848,1394,-7173,-5225,-8244,-809,8025,-4072,-4391,-9579,1407,6700,2421,-6685,5481,-1732,-8892,-6645,3077,3287,-4149,8701,-4393,-9070,-1777,2237,-3253,-506,-4931,-7366,-8132,5406,-6300,-275,-1908,67,3569,1433,-7262,-437,8303,4498,-379,3054,-6285,4203,6908,4433,3077,2288,9733,-8067,3007,9725,9669,1362,-2561,-4225,5442,-9006,-429,160,-9234,-4444,3586,-5711,-9506,-79,-4418,-4348,-5891};
//		int k = 93;
//		int[] nums = {-10_000, -10_000};
//		int k = 2;
//		int[] nums = {-10_000, 0};
//		int k = 2;
		System.out.println(new MaximumAverageSubarray_I().findMaxAverage(nums, k));
	}
		public double findMaxAverage(int[] nums, int k) {
        int idx = 0, j = k;
        double maxAverage = -10_000 * nums.length;
        double sum = 0.0;
        for(int i = 0; i < j; i++) {
        	sum += (double) nums[i];
        }
        double curAverage = sum / k;
    	maxAverage = Math.max(maxAverage, curAverage);
    	idx++; j++;
        while(j <= nums.length) {
        	sum = sum + nums[j - 1] - nums[idx - 1]; 
        	curAverage = sum / k;
        	maxAverage = Math.max(maxAverage, curAverage);
        	idx++; j++;
        }
        return maxAverage;
    }	
}
