package aq.gym.contests.binary_tree;

public class TreeNode {
	
	protected int val;
	protected TreeNode left;
	protected TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "" + val;
	}
}
