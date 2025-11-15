package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumLevelSumOfBinaryTree {

//	https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree
	public static void main(String[] args) {
//		Case #1 (2)
//		TreeNode root = new TreeNode(1);
//		TreeNode n7_1 = new TreeNode(7);
//		TreeNode n0 = new TreeNode(0);
//		root.left = n7_1; root.right = n0;
//		TreeNode n7_2 = new TreeNode(7);
//		TreeNode n8 = new TreeNode(-8);
//		n7_1.left = n7_2;
//		n7_1.right = n8;
//		Case #2 (2)
//		TreeNode root = new TreeNode(989);
//		TreeNode lvl2r = new TreeNode(10250);
//		root.right =  lvl2r;
//		TreeNode lvl3l = new TreeNode(98693);
//		TreeNode lvl3r = new TreeNode(-89388);
//		lvl2r.left = lvl3l;
//		lvl2r.right = lvl3r;
//		TreeNode lvl4r = new TreeNode(-32127);
//		lvl3r.right = lvl4r;
//		Case #3 (3)
		TreeNode root = new TreeNode(-100);
		TreeNode lvl2l = new TreeNode(-200);
		TreeNode lvl2r = new TreeNode(-300);
		root.left = lvl2l; root.right = lvl2r;
		TreeNode lvl3l_1 = new TreeNode(-20);
		TreeNode lvl3r_1 = new TreeNode(-5);
		lvl2l.left = lvl3l_1; lvl2l.right = lvl3r_1;
		TreeNode lvl3l_2 = new TreeNode(-10);
		lvl2r.left = lvl3l_2;
		System.out.println(new MaximumLevelSumOfBinaryTree().maxLevelSum(root));
	}

    public int maxLevelSum(TreeNode root) {
    	int currentLevelSum = 0, currentLevel = 0;
    	PriorityQueue<int[]> levelsSumPQ = new PriorityQueue<>((arr1, arr2) -> {
    		if(arr1[1] > arr2[1]) {
    			return Integer.compare(arr2[1], arr1[1]);
    		} else {
    			return Integer.compare(arr1[0], arr2[0]);
    		}
    	}); 
    	Queue<TreeNode> currLevelNodes = new ArrayDeque<>();
    	Queue<TreeNode> nextLevelNodes = new ArrayDeque<>();
    	currLevelNodes.offer(root);
    	while(!currLevelNodes.isEmpty()) {
    		TreeNode node = currLevelNodes.poll();
    		currentLevelSum += node.val;
    		if(node.left != null) nextLevelNodes.offer(node.left);
    		if(node.right != null) nextLevelNodes.offer(node.right);
    		if(currLevelNodes.isEmpty()) {
    			currentLevel++;
    			currLevelNodes = nextLevelNodes;
    			nextLevelNodes = new ArrayDeque<>();
    			levelsSumPQ.offer(new int[] {currentLevel, currentLevelSum});
    			currentLevelSum = 0;
    		}
    	}
    	return levelsSumPQ.poll()[0];
    }
}
