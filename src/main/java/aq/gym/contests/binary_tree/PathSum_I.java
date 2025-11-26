package aq.gym.contests.binary_tree;

import java.util.HashSet;
import java.util.Set;

public class PathSum_I {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		TreeNode n2 = new TreeNode(4);
		TreeNode n3 = new TreeNode(8);
		TreeNode n4 = new TreeNode(11);
		TreeNode n5 = new TreeNode(13);
		TreeNode n6 = new TreeNode(4);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(2);
		TreeNode n9 = new TreeNode(1);
		root.left = n2; root.right = n3;
		n2.left = n4;
		n4.left = n7; n4.right = n8;
		n3.left = n5; n3.right = n6;
		n6.right = n9;
		System.out.println(new PathSum_I().hasPathSumRecursivelyAdvenced(root, 22));
	}

	public boolean hasPathSumRecursively(TreeNode root, int targetSum) {
		if(root == null) 
			return false;
		if(root.left == null && root.right == null && targetSum != root.val) 
			return false;
        Set<Integer> sums = new HashSet<Integer>();
        hasPathSumRecursively(root, targetSum, 0, sums);
        return sums.contains(targetSum);
    }
	
	private void hasPathSumRecursively(TreeNode node, int targetSum, int currentSum, Set<Integer> sums) {
		if(node == null) 
			return;
		if(node.left == null && node.right == null) {
			currentSum += node.val;
			sums.add(currentSum);
			return;
		}
		hasPathSumRecursively(node.left, targetSum, currentSum + node.val, sums);
		hasPathSumRecursively(node.right, targetSum, currentSum + node.val, sums);
	}
	
	public boolean hasPathSumRecursivelyAdvenced(TreeNode root, int targetSum) {
		if(root == null) 
			return false;
		if(root.left == null && root.right == null && targetSum != root.val) 
			return false;
		return hasPathSumRecursivelyAdvenced(root, targetSum, 0);
	}
	
	private boolean hasPathSumRecursivelyAdvenced(TreeNode node, int targetSum, int currentSum) {
		if(node == null) {			
			return false;
		}
		if(node.left == null && node.right == null) {
			currentSum += node.val;
			return currentSum == targetSum;
		}
		return hasPathSumRecursivelyAdvenced(node.left, targetSum, currentSum + node.val) ||
				hasPathSumRecursivelyAdvenced(node.right, targetSum, currentSum + node.val);
	}
}
