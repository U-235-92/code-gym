package aq.gym.algorithms_and_structures.tree.simple_array_tree;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Tree {

	
	private int numberOfNodes;
	private int[] nodes = null;
	
	public Tree(int n, int rootValue) {
		numberOfNodes = n;
		nodes = IntStream.iterate(-1, num -> -1).limit(numberOfNodes).toArray();
		nodes[0] = rootValue;
	}
	
	private void putRight(int value, int root) {
		root -= 1;
		int position = (root * 2) + 2;
		nodes[position] = value;
	}
	
	private void putLeft(int value, int root) {
		root -= 1;
		int position = (root * 2) + 1;
		nodes[position] = value;
	}
	
	private int getIndex(int node) {
		int idx;
		if(node % 2 == 0) {
			idx = (node / 2) + 1;
		} else {
			idx = (node / 2) + 2;
		}
		return idx;
	}
	
	private void print() {
		Arrays.stream(nodes).forEach(node -> {
			if(node != -1)
				System.out.print(node + " ");
			else
				System.out.print("- ");
		});
		System.out.println();
	}
	
	public static void main(String[] args) {
		Tree tree = new Tree(10, 1);
		tree.putLeft(2, 1);
		tree.putRight(3, 1);
		tree.putLeft(4, 2);
		tree.putRight(5, 2);
		tree.putLeft(8, 4);
		tree.putRight(9, 4);
		tree.putLeft(10, 5);
		tree.putRight(6, 3);
		tree.putLeft(7, 3);
		tree.print();
	}
}
