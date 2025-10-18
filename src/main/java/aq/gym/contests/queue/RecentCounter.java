package aq.gym.contests.queue;

import java.util.ArrayDeque;

public class RecentCounter {

	private ArrayDeque<Integer> queue;
	private int counter = 0;
	
    public RecentCounter() {
        queue = new ArrayDeque<>();
    }
    
    public int ping(int t) {
    	queue.add(t);
    	while(!queue.isEmpty()) {
    		int curr = queue.peek();
    		if(isInRange(t, curr)) {
    			counter++;
    			break;
    		} else {
    			queue.poll();
    			counter--;
    		}
    	}
        return counter;
    }
	
    private boolean isInRange(int time, int current) {
    	return current >= (time - 3000) && current <= time; 
    }
    
//  https://leetcode.com/problems/number-of-recent-calls
	public static void main(String[] args) {
		RecentCounter rc = new RecentCounter();
		System.out.println(rc.ping(1));
		System.out.println(rc.ping(100));
		System.out.println(rc.ping(3001));
		System.out.println(rc.ping(3002));
	}
}
