package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Subsets {

//	https://leetcode.com/problems/subsets/description/
	public static void main(String[] args) {
		int[] nums = {1,2,3}; // [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//		int[] nums = {1,2}; // [[],[1],[2],[1,2]]
		System.out.println(new Subsets().subsets(nums));
	}

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        ArrayList<Integer> subset = new ArrayList<Integer>();
        makeSubsets(0, subsets, subset, nums); 
        return subsets;
    }
	
    public void makeSubsets(int idx, List<List<Integer>> subsets, ArrayList<Integer> subset, int[] nums) {
        subsets.add(new ArrayList<Integer>(subset));
        for(int i = idx; i < nums.length; i++) {
            subset.add(nums[i]);
            makeSubsets(i + 1, subsets, subset, nums);
            subset.remove(subset.size() - 1);
        }
    }
    
    private void makeSubsets1(Set<List<Integer>> subsets, List<Integer> nums, List<Integer> subset) {
    	if(nums.isEmpty()) {
    		return;
    	}
    	subset.add(nums.removeLast());
    	makeSubsets1(subsets, new ArrayList<Integer>(nums), new ArrayList<Integer>(subset));
    	subsets.add(nums);
    	if(!nums.isEmpty()) {    		
    		makeSubsets1(subsets, new ArrayList<Integer>(subset), new ArrayList<Integer>());
    	}
    	subsets.add(subset);
    }
}
