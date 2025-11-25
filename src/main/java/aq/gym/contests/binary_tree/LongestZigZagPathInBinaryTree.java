package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LongestZigZagPathInBinaryTree {

//	https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
	public static void main(String[] args) {
//		TreeNode root = new TreeNode(0);
//		TreeNode n1 = new TreeNode(1);
//		root.right = n1;		
//		TreeNode n2 = new TreeNode(2);
//		TreeNode n3 = new TreeNode(3);
//		n1.left = n2; n1.right = n3;
//		TreeNode n4 = new TreeNode(4);
//		TreeNode n5 = new TreeNode(5);
//		n3.left = n4; n3.right = n5;
//		TreeNode n6 = new TreeNode(6);
//		n4.right = n6;
//		TreeNode n7 = new TreeNode(7);
//		n6.right = n7;
//		System.out.println(new LongestZigZagPathInBinaryTree().longestZigZag(root)); // 3
		
//		TreeNode root = new TreeNode(1);
//		TreeNode n1 = new TreeNode(1);
//		TreeNode n2 = new TreeNode(1);
//		root.left = n1; root.right = n2;
//		TreeNode n3 = new TreeNode(1);
//		n1.right = n3;
//		TreeNode n4 = new TreeNode(1);
//		TreeNode n5 = new TreeNode(1);
//		n3.left = n4; n3.right = n5;
//		TreeNode n6 = new TreeNode(1);
//		n4.right = n6;
//		System.out.println(new LongestZigZagPathInBinaryTree().longestZigZag(root)); // 4
		
		TreeNode root = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		root.right = n1;
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		n1.left = n2; n1.right = n3;
		TreeNode n4 = new TreeNode(4);
		n3.right = n4;
		System.out.println(new LongestZigZagPathInBinaryTree().longestZigZag(root)); // 2
	}

    public int longestZigZag(TreeNode root) {
    	PriorityQueue<Integer> zigZagDepthsPQ = new PriorityQueue<>(Comparator.reverseOrder());
    	Queue<Object[]> rootsPaths = new ArrayDeque<>();
    	rootsPaths.offer(new Object[] {root, "right"});
        rootsPaths.offer(new Object[] {root, "left"});
    	while(!rootsPaths.isEmpty()) {
    		Object[] data = rootsPaths.poll();
    		TreeNode node = (TreeNode) data[0];
    		String direction = (String) data[1];
    		longestZigZag(node, direction, 0, zigZagDepthsPQ, rootsPaths);
    	}
    	return zigZagDepthsPQ.poll();
    }
    
    private void longestZigZag(TreeNode node, String direction, int zigZagDepth, PriorityQueue<Integer> zigZagDepthsPQ, Queue<Object[]> rootsPaths) {
    	if(node == null) {
    		zigZagDepthsPQ.offer(zigZagDepth);
    		return;
    	}
    	if(direction.equals("right")) {
    		if(node.right != null) {
    			rootsPaths.offer(new Object[] {node.right, "left"});
    		}
    		if(node.left != null) {
    			longestZigZag(node.left, "left", zigZagDepth + 1, zigZagDepthsPQ, rootsPaths);
    		} else {
    			zigZagDepthsPQ.offer(zigZagDepth);
        		return;
    		}
    	} else {
    		if(node.left != null) {
    			rootsPaths.offer(new Object[] {node.left, "right"});
    		} 
    		if(node.right != null) {
    			longestZigZag(node.right, "right", zigZagDepth + 1, zigZagDepthsPQ, rootsPaths);
    		} else {
    			zigZagDepthsPQ.offer(zigZagDepth);
        		return;
    		}
    	}
    }
}
