package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostorderTraversal {

	public static void main(String[] args) {
		firstExample();
		secondExample();
		thirdExample();
		fourthExample();
	}
	
	private static void firstExample() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.right = n2;
		n2.left = n3;
		System.out.println(new BinaryTreePostorderTraversal().postorderTraversalRecursively(root));
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
		System.out.println(new BinaryTreePostorderTraversal().postorderTraversalIteratively(root));
	}
	
	private static void thirdExample() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.left = n2;
		n2.left = n3;
		System.out.println(new BinaryTreePostorderTraversal().postorderTraversalRecursively(root));
	}
	
	private static void fourthExample() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.right = n2;
		n2.right = n3;
		System.out.println(new BinaryTreePostorderTraversal().postorderTraversalIteratively(root));
	}

	public List<Integer> postorderTraversalIteratively(TreeNode root) {
		List<Integer> postorderTraverse = new ArrayList<>();
		if(root == null) {
			return postorderTraverse; 
		}
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
        	TreeNode node = stack.pop();
        	postorderTraverse.add(node.val);
        	if(node.left != null) {
        		stack.push(node.left);
        	}
        	if(node.right != null) {
        		stack.push(node.right);
        	}
        }
        return postorderTraverse.reversed();
    }
	
	public List<Integer> postorderTraversalRecursively(TreeNode root) {
		List<Integer> postorderTraverse = new LinkedList<>();
		traverse(root, postorderTraverse);
        return postorderTraverse;
    }
	
	private void traverse(TreeNode node, List<Integer> traverse) {
		if(node == null)
			return;
		traverse(node.left, traverse);
		traverse(node.right, traverse);
		traverse.add(node.val);
	}
}
