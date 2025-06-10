package aq.gym.contests.hash_collection;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

	public static void main(String[] args) {
		int[] nums = {0,4,5,0,3,6};
		System.out.println(new ContainsDuplicate().containsDuplicate(nums));
	}

	public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        boolean isContainDublicates = false;
        for(int num : nums) {
        	isContainDublicates = set.add(num);
        	if(!isContainDublicates)
        		break;
        }
        return !isContainDublicates;
    }
}
