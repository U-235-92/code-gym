package aq.gym.contests.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeSymmetryDetector {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n4 = new TreeNode(3);
		TreeNode n5 = new TreeNode(4);
		TreeNode n3 = new TreeNode(2);
		TreeNode n6 = new TreeNode(3);
		TreeNode n7 = new TreeNode(4);
		root.left = n2; root.right = n3;
		n2.left = n4; n2.right = n5;
		n3.left = n7; n3.right = n6;
		System.out.println(new TreeSymmetryDetector().isSymmetric(n7));
	}

	public boolean isSymmetric(TreeNode root) {
		if(root == null) 
			return false;
		if(root.left == null && root.right == null) 
			return true;
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		leftSubtreeTraverse(root.left, left);
		rightSubtreeTraverse(root.right, right);
		return left.equals(right);
	}

	private void leftSubtreeTraverse(TreeNode node, List<Integer> traverse) {
		if(node == null) {
			traverse.add(null);
			return;
		}
		leftSubtreeTraverse(node.left, traverse);
		leftSubtreeTraverse(node.right, traverse);
		traverse.add(node.val);
	}

	private void rightSubtreeTraverse(TreeNode node, List<Integer> traverse) {
		if(node == null) {
			traverse.add(null);
			return;
		}
		rightSubtreeTraverse(node.right, traverse);
		rightSubtreeTraverse(node.left, traverse);
		traverse.add(node.val);
	}
}
