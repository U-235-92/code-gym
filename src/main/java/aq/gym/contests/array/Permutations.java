package aq.gym.contests.array;

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

    public void permutation(List<List<Integer>> ans, List<Integer> take, Queue<Integer> remain) {
        if (remain.isEmpty()) {
            ans.add(new ArrayList<>(take));
            return;
        }

        int curr = remain.poll();
        for (int i = 0; i <= take.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>(take);
            temp.add(i, curr);
            permutation(ans, temp, remain);
        }
        remain.add(curr);
    }
}
