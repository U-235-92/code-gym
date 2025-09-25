package aq.gym.contests.array;

public class MedianOfTwoSortedArrays {

//	https://leetcode.com/problems/median-of-two-sorted-arrays/description/
	public static void main(String[] args) {
		int[] nums1 = {5, 8};
		int[] nums2 = {8, 5};
		System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2));
	}

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int[] merge = merge(nums1, nums2);
    	if(merge.length % 2 == 0) {
    		int a = merge[merge.length / 2];
    		int b = merge[(merge.length / 2) - 1];
    		return (double) (a + b) / 2;
    	} else {
    		return (double) merge[merge.length / 2];
    	}
    }
    
    private int[] merge(int[] nums1, int[] nums2) {
    	int[] merge = new int[nums1.length + nums2.length];
    	int i = 0, j = 0, m = 0;
    	while(i < nums1.length && j < nums2.length) {
    		if(nums1[i] <= nums2[j]) {
    			merge[m] = nums1[i];
    			m++; i++;
    		} else if(nums1[i] > nums2[j]) {
    			merge[m] = nums2[j];
    			m++; j++;
    		}
    	}
    	while(i < nums1.length) {
    		merge[m] = nums1[i];
			m++; i++;
    	}
    	while(j < nums2.length) {
			merge[m] = nums2[j];
			m++; j++;
    	}
    	return merge;
    }
}
