package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BeautifulArrangement {

//	https://leetcode.com/problems/beautiful-arrangement/
	public static void main(String[] args) {
		int n = 15;
		System.out.println(new BeautifulArrangement().countArrangement(n));
	}

    public int countArrangement(int n) {
    	List<List<Integer>> permutations = new ArrayList<List<Integer>>();
    	boolean[] visited = new boolean[n + 1];
    	countArrangement(permutations, new ArrayList<Integer>(), visited, n);
    	return permutations.size();
    }
    
    private void countArrangement(List<List<Integer>> permutations, List<Integer> permutation, boolean[] visited, final int n) {
    	if(permutation.size() == n) {
    		permutations.add(new ArrayList<Integer>(permutation));
    		return;
    	}
    	for(int i = 0; i < n; i++) {
    		if(visited[i]) continue;
    		int num = permutation.size() + 1;
    		if(num % (i + 1) == 0 || (i + 1) % num == 0) {    			
    			permutation.add(i);
    			visited[i] = true;
    			countArrangement(permutations, permutation, visited, n);
    			visited[i] = false;
    			permutation.removeLast();
    		}
    	}
    }
}
