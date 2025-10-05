package aq.gym.contests.array;

public class MajorityElement {
//	https://leetcode.com/problems/majority-element/description/
	public static void main(String[] args) {
		int[] nums = {3,2,3};
		System.out.println(new MajorityElement().majorityElement(nums));
	}

    public int majorityElement(int[] nums) {
    	int majorityElement = 0, counter = 0;
    	for(int i = 0; i < nums.length; i++) {
    		if(counter == 0) {
    			majorityElement = nums[i];
    			counter++;
    		} else {
    			if(nums[i] == majorityElement) {
    				counter++;
    			} else {
    				counter--;
    			}
    		}
    	}
    	return majorityElement;
    }
}
