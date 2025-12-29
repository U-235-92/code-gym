package aq.gym.contests.binary_tree;

public class DiameterOfBinaryTree {

//	https://leetcode.com/problems/diameter-of-binary-tree/
	public static void main(String[] args) {
//		Case #1
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.left = n2; root.right = n3;
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n2.left = n4; n2.right = n5;
		TreeNode n6 = new TreeNode(6);
		n5.left = n6;
		System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTree(root));
		
//		Case #2
//		TreeNode root = new TreeNode(1);
//		TreeNode n2 = new TreeNode(2);
//		root.right = n2;
//		System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTree(root));
	}
	
    public int diameterOfBinaryTree(TreeNode root) {
    	int[] max = {0};
    	evaluateDiameter(root, max);
    	return max[0];
    }
    
    private int evaluateDiameter(TreeNode root, int[] max) {
    	if(root == null) {
    		return 0;
    	}
    	int left = evaluateDiameter(root.left, max);
    	int right = evaluateDiameter(root.right, max);
    	max[0] = Math.max(max[0], left + right);
    	return 1 + Math.max(left, right);
    }
}
