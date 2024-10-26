package aq.gym.algorithms_and_structures.structures.stack;

public class Stack {

	private int index;
	private int size;
	private int[] stack;
	
	public Stack(int size) {
		stack = new int[size];
		this.size = 0;
		index = 0;
	}
	
	public void push(int val) {
		if(!isFull()) {
			stack[index++] = val;
			size++;
			System.out.println("Push: " + val);
		} else {
			System.out.println("Stack is full");
		}
	}
	
	public void pop() {
		if(!isEmpty()) {
			int val = stack[--index];
			size--;
			System.out.println("Pop: " + val);
		} else {
			System.out.println("Stack is empty");
		}
	}
	
	private boolean isFull() {
		return size >= stack.length;
	}
	
	private boolean isEmpty() {
		return size <= 0;
	}
}
