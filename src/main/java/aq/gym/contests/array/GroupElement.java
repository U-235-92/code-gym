package aq.gym.contests.array;

import java.util.Arrays;

public class GroupElement {

	public static void main(String[] args) {
		int val = 0;
		int[] nums = {0,1,2,2,3,0,4,2};
		int len = new GroupElement().removeElement(nums, val);
		System.out.println(len);
	}

	public int removeElement(int[] nums, int val) {
		if(nums.length == 0) {
			return 0;
		}
		int length = nums.length, i = 0, j = nums.length - 1;
		while(i <= j) {
			if((nums[i] == val || nums[i] != val) && nums[j] == val) {
				length--; j--;
			} else if(nums[i] == val && nums[j] != val) {
				int tmp = nums[i];
        		nums[i] = nums[j];
        		nums[j] = tmp;
        		length--; i++; j--;
			} else if(nums[i] != val && nums[j] != val) {
				i++;
			}
		}
		System.out.println(Arrays.toString(nums));
		return length;
    }
}
