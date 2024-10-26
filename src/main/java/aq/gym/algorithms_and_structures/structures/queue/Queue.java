package aq.gym.algorithms_and_structures.structures.queue;

public class Queue {

	private int[] queue;
	private int rear;
	private int front;
	private int size;

	public Queue(int size) {
		queue = new int[size];
		rear = 0;
		front = 0;
		this.size = 0;
	}
	
	public void insert(int val) {
		if(isFull()) {
			System.out.println("Queue is full");
		} else {
			if(front == queue.length - 1) {
				queue[front] = val;
				front = 0;
			} else {				
				queue[front++] = val;
			}
			size++;
			System.out.println("Add: " + val);
		}
	}

	public void remove() {
		if(isEmpty()) {
			System.out.println("Queue is empty");
		} else {
			int val = 0;
			if(rear == queue.length - 1) {
				val = queue[rear];
				rear = 0;
			} else {
				val = queue[rear++];
			}
			size--;
			System.out.println("Get: " + val);
		}
	}
	
	private boolean isFull() {
		return size == queue.length;
	}
	
	private boolean isEmpty() {
		return size == 0;
	}
}
