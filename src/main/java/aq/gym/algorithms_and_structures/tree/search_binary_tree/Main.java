package aq.gym.algorithms_and_structures.tree.search_binary_tree;

public class Main {

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.add(new Node(10));
		tree.add(new Node(8));
		tree.add(new Node(20));
		tree.add(new Node(5));
		tree.add(new Node(9));
		tree.add(new Node(1));
		tree.add(new Node(12));
		tree.add(new Node(23));
		tree.add(new Node(7));
		tree.add(new Node(11));
		tree.add(new Node(22));
		tree.add(new Node(28));
		tree.add(new Node(21));
		tree.add(new Node(30));
		tree.add(new Node(6));
		tree.travel();
		System.out.println("+++");
		tree.remove(28);
		tree.travel();
	}
}
