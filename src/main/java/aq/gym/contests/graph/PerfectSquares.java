package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class PerfectSquares {

	public static void main(String[] args) {
		int val = 13;
		System.out.println(new PerfectSquares().numSquares(val));
	}

    public int numSquares(int n) {
    	int bfsDeep = 1;
    	Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	for(int i = 0; i < size; i++) {
        		int value = queue.poll();
        		if(!visited.contains(value)) {        			
        			for(int j = 1; (int) Math.pow(j, 2) <= value; j++) {
        				int square = (int) Math.pow(j, 2);
        				if(value - square == 0) {
        					return bfsDeep;
        				}
        				queue.offer(value - square);
        			}
        			visited.add(value);
        		}
        	}
        	bfsDeep++;
        }
        return bfsDeep;
    }
}
