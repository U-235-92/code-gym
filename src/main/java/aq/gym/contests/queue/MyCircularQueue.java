package aq.gym.contests.queue;

import java.util.Arrays;

public class MyCircularQueue {

	private int head;
	private int tail;
	private int length;
	private final int[] queue;
	
	public MyCircularQueue(int k) {
		this.queue = new int[k];
		this.length = 0;
		this.head = -1;
		this.tail = -1;
	}
	
	public boolean enQueue(int value) {
		if(isEmpty()) {
			queue[++tail] = value;
			head++; length++;
			return true;
		} else if(isFull()) {
			return false;
		} else {			
			if(tail + 1 >= queue.length) {
				tail = 0;
				queue[tail] = value;
			} else {				
				queue[++tail] = value;
			}
			length++;
			return true;
		}
	}

	public boolean deQueue() {
		if(isEmpty()) {
			head = -1; tail = -1;
			return false;
		} else {			
			if(head + 1 >= queue.length) {
				head = 0;
			} else {
				head++;
			}
			length--;
			if(isEmpty()) {
				head = -1;
				tail = -1;
			}
			return true;
		}
	}

	public int front() {
		if(isEmpty()) {
			return -1;
		}
		return queue[head];
	}

	public int rear() {
		if(isEmpty()) {
			return -1;
		}
		return queue[tail];
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public boolean isFull() {
		return length == queue.length;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(queue);
	}
}
