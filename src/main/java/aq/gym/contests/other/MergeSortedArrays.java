package aq.gym.contests.other;

import java.util.Arrays;

public class MergeSortedArrays {

	public static void main(String[] args) {
		int[] nums1 = {0};
		int[] nums2 = {1};
		new MergeSortedArrays().merge(nums1, 0, nums2, 1);
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1, j = n - 1, pos = nums1.length - 1;
		while(i >= 0 && j >= 0) {
			if(nums2[j] > nums1[i]) {
				nums1[pos] = nums2[j];
				j--; pos--;
			} else {
				nums1[pos] = nums1[i];
				i--; pos--;
			}
		}
		while(i >= 0) {
			nums1[pos] = nums1[i];
			i--; pos--;
		}
		while(j >= 0) {
			nums1[pos] = nums2[j];
			j--; pos--;
		}
//		System.out.println(Arrays.toString(nums1));
    }
}
