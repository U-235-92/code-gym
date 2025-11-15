package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class MinimumDepthOfBinaryTree {

//	https://leetcode.com/problems/minimum-depth-of-binary-tree/
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.left = n2; root.right = n3;
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n2.left = n4; n2.right = n5;
		System.out.println(new MinimumDepthOfBinaryTree().minDepth(root));
	}

    public int minDepth(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
    	int depth = 0;
    	Queue<TreeNode> currLine = new ArrayDeque<>();
    	Queue<TreeNode> nextLine = new ArrayDeque<>();
    	currLine.add(root);
    	while(!currLine.isEmpty()) {
    		TreeNode node = currLine.poll();
    		if(node.left == null && node.right == null) {
    			return ++depth;
    		} else {
    			if(node.left != null) nextLine.add(node.left);
    			if(node.right != null) nextLine.add(node.right);
    		}
    		if(currLine.isEmpty()) {    			
    			depth++;
    			currLine = nextLine;
    			nextLine = new ArrayDeque<>();
    		}
    	}
    	return depth;
    }
}
