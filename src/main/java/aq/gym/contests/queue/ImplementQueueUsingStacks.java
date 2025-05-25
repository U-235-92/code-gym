package aq.gym.contests.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImplementQueueUsingStacks {

	private Deque<Integer> pushStack;
	private Deque<Integer> popStack;
	
	public ImplementQueueUsingStacks() {
		pushStack = new ArrayDeque<>();
		popStack = new ArrayDeque<>();
    }
	
	public void push(int x) {
		if(empty()) {
			popStack.push(x);
		} else {
			if(pushStack.isEmpty()) {
				pushStack.push(x);
			} else {
				while(!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
				pushStack.push(x);
				while(popStack.size() != 1) {
					pushStack.push(popStack.pop());
				}
			}
		}
	}

	public int pop() {
		int pop = popStack.pop();
		if(!pushStack.isEmpty()) {			
			popStack.push(pushStack.pop());
		}
		return pop;
	}

	public int peek() {
		return popStack.peek();
	}

	public boolean empty() {
		return pushStack.isEmpty() && popStack.isEmpty();
	}
	
	public static void main(String[] args) {
		
	}
}
