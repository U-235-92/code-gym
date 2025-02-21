package aq.gym.algorithms_and_structures.tree.simple_binary_tree;

public class Main {

	public static void main(String[] args) {
		Tree tree = new Tree(1);
		tree.insertRight(5);
		tree.insetLeft(8);
		tree.insertRight(3);
		tree.insetLeft(7);
		tree.insetLeft(4);
		tree.insertRight(9);
		tree.insetLeft(2);
		breadthTreeWalk(tree);
		deepPreTreeWalk(tree);
		deepPostTreeWalk(tree);
		deepInOrderTreeWalk(tree);
		inverseTree(tree);
		breadthTreeWalk(tree);
	}
	
	private static void breadthTreeWalk(Tree tree) {
		tree.breadthWalk();
		System.out.println(" <- Breadth walk");
	}

	private static void deepPreTreeWalk(Tree tree) {
		tree.deepPreOrderWalk();
		System.out.println(" <- Pre order walk");
	}
	
	private static void deepPostTreeWalk(Tree tree) {
		tree.deepPostOrderWalk();
		System.out.println(" <- Post order walk");
	}
	
	private static void deepInOrderTreeWalk(Tree tree) {
		tree.deepInOrderWalk();
		System.out.println(" <- In order walk");
	}
	
	private static void inverseTree(Tree tree) {
		System.out.println("Inverse the tree");
		tree.inverse();
	}
}
