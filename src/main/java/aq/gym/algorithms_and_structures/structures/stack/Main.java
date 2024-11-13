package aq.gym.algorithms_and_structures.structures.stack;

public class Main {

	public static void main(String[] args) {
		Stack stack = new Stack(3);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.pop();
		stack.push(4);
		stack.pop();
		stack.pop();
		stack.pop();
	}

}
