package aq.gym.contests.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Permutations {

	public static void main(String[] args) {
		int[] nums = {1,2,3};
		System.out.println(new Permutations().permute(nums));
	}
	
	public List<List<Integer>> permute(int[] nums) {
        Queue<Integer> remain = new LinkedList<>();
        for (int a : nums) {
            remain.add(a);
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> take = new ArrayList<>();
        permutation(ans, take, remain);
        return ans;
    }

    public void permutation(List<List<Integer>> permutations, List<Integer> currentPermutation, Queue<Integer> elementsToPermute) {
        if (elementsToPermute.isEmpty()) {
            permutations.add(new ArrayList<>(currentPermutation));
            return;
        }

        int curr = elementsToPermute.poll();
        for (int i = 0; i <= currentPermutation.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>(currentPermutation);
            temp.add(i, curr);
            permutation(permutations, temp, elementsToPermute);
        }
        elementsToPermute.add(curr);
    }
}
