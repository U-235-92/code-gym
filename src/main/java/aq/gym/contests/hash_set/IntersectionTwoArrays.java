package aq.gym.contests.hash_set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionTwoArrays {

	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1}, nums2 = {2,2};
		System.out.println(Arrays.toString(new IntersectionTwoArrays().intersection(nums1, nums2)));
	}

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> aSet = new HashSet<>(Arrays.stream(nums1).boxed().toList());
        Set<Integer> bSet = new HashSet<>(Arrays.stream(nums2).boxed().toList());
        aSet.retainAll(bSet);
        return aSet.stream().mapToInt(Integer::valueOf).toArray();
    }
}
