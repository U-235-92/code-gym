package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
	
	private Deque<Integer> valStack;
	private Deque<Integer> minStack;
	
	public MinStack() {
		super();
		valStack = new ArrayDeque<>();
		minStack = new ArrayDeque<>();
	}

	public void push(int val) {
		valStack.push(val);
		if(minStack.isEmpty()) {
			minStack.push(val);
		} else {
			int peek = minStack.peek();
			if(val <= peek) {
				minStack.push(val);
			} else {
				minStack.push(peek);
			}
		}
    }
    
    public void pop() {
        valStack.pop();
        minStack.pop();
    }
    
    public int top() {
    	return valStack.peek();
    }
    
    public int getMin() {
       return minStack.peek();
    }
}
