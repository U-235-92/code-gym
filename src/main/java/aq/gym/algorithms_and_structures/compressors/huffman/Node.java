package aq.gym.algorithms_and_structures.compressors.huffman;

import lombok.Data;

@Data
public class Node implements Comparable<Node> {

	private Node left;
	private Node right;
	private long frequency;
	private String letter;

	public static Node compose(Node left, Node right) {
		Node union = new Node();
		union.left = left;
		union.right = right;
		union.frequency = left.frequency + right.frequency;
		return union;
	}

	public boolean isLeaf() {
		return right == null && left == null;
	}

	@Override
	public int compareTo(Node node) {
		return Long.compare(frequency, node.frequency);
	}

}
