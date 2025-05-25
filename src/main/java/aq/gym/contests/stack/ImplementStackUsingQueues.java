package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class ImplementStackUsingQueues {

	private Queue<Integer> pollQueue;
	private Queue<Integer> offerQueue;
	
	public ImplementStackUsingQueues() {
        pollQueue = new ArrayDeque<>();
        offerQueue = new ArrayDeque<>();
    }
    
    public void push(int x) {
    	if(empty()) {
    		pollQueue.offer(x);
    	} else {
    		if(offerQueue.isEmpty()) {
    			offerQueue.offer(x);
    			Queue<Integer> tmp = null;
    			tmp = offerQueue;
    			offerQueue = pollQueue;
    			pollQueue = tmp;
    		} else {
    			while(!offerQueue.isEmpty()) {
    				pollQueue.offer(offerQueue.poll());
    			}
    			offerQueue.offer(x);
    			Queue<Integer> tmp = null;
    			tmp = pollQueue;
    			pollQueue = offerQueue;
    			offerQueue = tmp;
    		}
    	}
    }
    
    public int pop() {
        int pop = pollQueue.poll();
        if(!offerQueue.isEmpty()) {        	
        	pollQueue.offer(offerQueue.poll());
        }
        return pop;
    }
    
    public int top() {
        return pollQueue.peek();
    }
    
    public boolean empty() {
        return pollQueue.isEmpty() && offerQueue.isEmpty();
    }
	
	public static void main(String[] args) {
		
	}
}
