package aq.gym.contests.array;

public class SearchInsertPosition {

	public static void main(String[] args) {
		int[] nums = {1, 3, 5, 6};
		System.out.println(new SearchInsertPosition().searchInsert(nums, 8)); 
	}

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
        	int mid = left + (right - left) / 2;
        	if(nums[mid] == target) {
        		return mid;
        	} else {
        		if(right - left == 0) {
        			if(target > nums[left]) {
        				return left + 1;
        			} else {
        				if(target < nums[right]) {
        					return right;
        				} else {        					
        					return right + 1;
        				}
        			}
        		} else {
        			if(nums[mid] > target) {
                		right = mid - 1;
                	} else {
                		left = left + 1;
                	}
        		}
        	}
        }
        if(target < nums[0]) {
        	return 0;
        } else {
        	return nums.length;
        }
    }
}
