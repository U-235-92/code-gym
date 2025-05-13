package aq.gym.contests.tree;

public class MaximumDepthBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(20);
		TreeNode n4 = new TreeNode(15);
		TreeNode n5 = new TreeNode(7);
		root.left = n2; root.right = n3;
		n3.left = n4; n3.right = n5;
		System.out.println(new MaximumDepthBinaryTree().maxDepth(n3));
	}

	public int maxDepth(TreeNode root) {
		if(root == null)
			return 0;
		if(root.left == null && root.right == null)
			return 1;
		Wrap wrap = new Wrap();
		maxDepth0(root, wrap, 1);
		return wrap.value;
	}
	
	private void maxDepth0(TreeNode root, Wrap wrap, int depth) {
		if(root == null)
			return;
		if(root.left == null && root.right == null)
			wrap.value = Integer.max(depth, wrap.value);
		maxDepth0(root.left, wrap, depth + 1);
		maxDepth0(root.right, wrap, depth + 1);
	}
	
	private class Wrap {
		
		private int value;
		
		Wrap() {
			super();
			value = 0;
		}
	}
}
