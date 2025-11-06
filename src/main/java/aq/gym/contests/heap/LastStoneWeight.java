package aq.gym.contests.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {

//	https://leetcode.com/problems/last-stone-weight/description/
	public static void main(String[] args) {
		int[] stones = {2,7,4,1,8,1};
		System.out.println(new LastStoneWeight().lastStoneWeight(stones));
	}

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(Arrays.stream(stones).mapToObj(Integer::valueOf).toList());
        return getLastStoneWeight(pq);
    }
    
    private int getLastStoneWeight(PriorityQueue<Integer> stones) {
    	while(stones.size() >= 2) {
    		int xStone = stones.poll();
        	int yStone = stones.poll();
        	if(xStone != yStone) {
        		stones.offer(xStone - yStone);
        	} 
    	}
    	if(stones.size() == 2) {
    		return stones.poll() - stones.poll();
    	}
    	if(stones.size() == 1) {
    		return stones.poll();
    	}
    	return 0;
    }
}
