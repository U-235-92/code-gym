package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Permutations {

	public static void main(String[] args) {
		int[] nums = {1,2,3};
		System.out.println(new Permutations().permute(nums));
		String line = "ABC";
		new Permutations().permute(line, 0, line.length() - 1);
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
    
    private void permute(String str, int l, int r) {
        if (l == r) {
            System.out.println(str);
        } else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }
 
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
