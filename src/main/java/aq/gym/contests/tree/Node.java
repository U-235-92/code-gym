package aq.gym.contests.tree;

public class Node {
	
	protected int val;
	protected Node left;
	protected Node right;
	protected Node next;

	public Node() {
	}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}
	
	@Override
	public String toString() {
		return "" + val;
	}
}
