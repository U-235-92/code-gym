package aq.gym.contests.binary_tree;

public class BalancedBinaryTree {

//	https://leetcode.com/problems/balanced-binary-tree/
	public static void main(String[] args) {
//		Case#1: [3,9,20,null,null,15,7] will return [true]
//		TreeNode root = new TreeNode(3);
//		TreeNode n9 = new TreeNode(9);
//		TreeNode n20 = new TreeNode(20);
//		TreeNode n15 = new TreeNode(15);
//		TreeNode n7 = new TreeNode(7);
//		root.left = n9;
//		root.right = n20;
//		n20.left = n15;
//		n20.right = n7;
//		System.out.println(new BalancedBinaryTree().isBalanced(root));
		
//		Case#2: [1,2,2,3,3,null,null,4,4] will return [false]
//		TreeNode root = new TreeNode(1);
//		TreeNode n1 = new TreeNode(2);
//		TreeNode n2 = new TreeNode(2);
//		root.left = n1; root.right = n2;
//		TreeNode n3 = new TreeNode(3);
//		TreeNode n4 = new TreeNode(3);
//		n1.left = n3; n1.right = n4;
//		TreeNode n5 = new TreeNode(4);
//		TreeNode n6 = new TreeNode(4);
//		n3.left = n5; n3.right = n6;
//		System.out.println(new BalancedBinaryTree().isBalanced(root));
		
//		Case#3: [1,2,2,3,null,null,3,4,null,null,4] will return [false]
//		TreeNode root = new TreeNode(1);
//		TreeNode n1 = new TreeNode(2);
//		TreeNode n2 = new TreeNode(2);
//		root.left = n1; root.right = n2;
//		TreeNode n3 = new TreeNode(3);
//		n1.left = n3;
//		TreeNode n4 = new TreeNode(3);
//		n2.right = n4;
//		TreeNode n5 = new TreeNode(4);
//		n3.left = n5;
//		TreeNode n6 = new TreeNode(4);
//		n4.right = n6;
//		System.out.println(new BalancedBinaryTree().isBalanced(root));
		
//		Case#4: [1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5] will return [true]
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(2);
		root.left = n1; root.right = n2;
		
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(3);
		n1.left = n3; n1.right = n4;
		TreeNode n5 = new TreeNode(3);
		TreeNode n6 = new TreeNode(3);
		n2.left = n5; n2.right = n6;
		
		TreeNode n7 = new TreeNode(4);
		TreeNode n8 = new TreeNode(4);
		n3.left = n7; n3.right = n8;
		TreeNode n9 = new TreeNode(4);
		TreeNode n10 = new TreeNode(4);
		n4.left = n9; n4.right = n10;
		TreeNode n11 = new TreeNode(4);
		TreeNode n12 = new TreeNode(4);
		n5.left = n11; n5.right = n12;
		
		TreeNode n13 = new TreeNode(5);
		TreeNode n14 = new TreeNode(5);
		n7.left = n13; n7.right = n14;
		System.out.println(new BalancedBinaryTree().isBalanced(root));
	}

    public boolean isBalanced(TreeNode root) {
    	if(root == null) 
    		return true;
    	boolean[] isDiffLessThanOne = {true};
    	int heighL = getMaxHightDiff(root.left, isDiffLessThanOne);
    	int heighR = getMaxHightDiff(root.right, isDiffLessThanOne);
    	int heightD = Math.abs(heighL - heighR);
    	return heightD <= 1 && isDiffLessThanOne[0];
    }
    
    private int getMaxHightDiff(TreeNode node, boolean[] isDiffLessThanOne) {
    	if(node == null) return 0;
    	int heighL = getMaxHightDiff(node.left, isDiffLessThanOne) + 1;
    	int heighR = getMaxHightDiff(node.right, isDiffLessThanOne) + 1;
    	int heighD = Math.max(heighL, heighR);
    	if(Math.abs(heighL - heighR) > 1) {
    		isDiffLessThanOne[0] = false;
    	}
    	return heighD;
    }
}
