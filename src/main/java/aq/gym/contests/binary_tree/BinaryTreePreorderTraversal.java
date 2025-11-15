package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorderTraversal {

	public static void main(String[] args) {
		firstExample();
		secondExample();
	}
	
	private static void firstExample() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.right = n2;
		n2.left = n3;
		System.out.println(new BinaryTreePreorderTraversal().preorderTraversalIteratively(root));
	}

	private static void secondExample() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);
		root.left = n2; root.right = n3;
		n2.left = n4; n2.right = n5;
		n5.left = n6; n5.right = n7;
		n3.right = n8;
		n8.left = n9;
		System.out.println(new BinaryTreePreorderTraversal().preorderTraversalRecursively(root));
	}
	
	public List<Integer> preorderTraversalIteratively(TreeNode root) {
		List<Integer> preorderTraverse = new ArrayList<>();
		if(root == null) 
			return preorderTraverse;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
        	TreeNode node = stack.pop();
        	preorderTraverse.add(node.val);
        	if(node.right != null) stack.push(node.right);
        	if(node.left != null) stack.push(node.left);
        }
        return preorderTraverse;
    }
	
	public List<Integer> preorderTraversalRecursively(TreeNode root) {
		List<Integer> traverse = new LinkedList<>();
		traverse(root, traverse);
		return traverse;
	}
	
	private void traverse(TreeNode node, List<Integer> traverse) {
		if(node == null) 
			return;
		traverse.add(node.val);
		traverse(node.left, traverse);
		traverse(node.right, traverse);
	}
}
