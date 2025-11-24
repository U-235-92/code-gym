package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

//	https://leetcode.com/problems/binary-tree-right-side-view/
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.left = n2; root.right = n3;
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n2.right = n5; n3.right = n4;
		TreeNode n6 = new TreeNode(6);
		n5.right = n6;
		List<Integer> rightSideView = new BinaryTreeRightSideView().rightSideView(root);
		System.out.println(rightSideView);
	}
	
    public List<Integer> rightSideView(TreeNode root) {
    	if(root == null) {
    		return Collections.emptyList();
    	}
    	boolean isRightNodeOnLevelSelected = false;
    	List<Integer> rightSideView = new LinkedList<>();
    	Queue<TreeNode> nextLevelNodes = new ArrayDeque<>();
    	Queue<TreeNode> currLevelNodes = new ArrayDeque<>();
    	currLevelNodes.offer(root);
    	while(!currLevelNodes.isEmpty()) {
    		TreeNode node = currLevelNodes.poll();
    		if(node == root) {
    			rightSideView.addLast(node.val);
    			if(node.right != null) {    				
    				currLevelNodes.offer(node.right);
    			}
    			if(node.left != null) {    				
    				currLevelNodes.offer(node.left);
    			}
    		} else {
    			if(!isRightNodeOnLevelSelected) {
        			rightSideView.addLast(node.val);
        			isRightNodeOnLevelSelected = true;
        		}
    			if(node.right != null) {
    				nextLevelNodes.offer(node.right);
    			}
    			if(node.left != null) {
    				nextLevelNodes.offer(node.left);
    			}
    			if(currLevelNodes.isEmpty()) {
    				currLevelNodes = nextLevelNodes;
    				nextLevelNodes = new ArrayDeque<>();
    				isRightNodeOnLevelSelected = false;
    			}
    		}
    	}
    	return rightSideView;
    }
}
