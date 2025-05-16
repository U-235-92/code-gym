package aq.gym.contests.tree;

import java.util.Arrays;

public class ConstructBinaryTreeFromPreorderInorder {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	private static void test1() {
		int[] inorder = {9,3,15,20,7};
		int[] preorder = {3,9,20,15,7};
		TreeNode root = new ConstructBinaryTreeFromPreorderInorder().buildTree(preorder, inorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test2() {
		int[] inorder = {5,9,3};
		int[] preorder = {3,9,5};
		TreeNode root = new ConstructBinaryTreeFromPreorderInorder().buildTree(preorder, inorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test3() {
		int[] inorder = {2,4,5,8,3,15,7,20};
		int[] preorder = {3,5,4,2,8,7,15,20};
		TreeNode root = new ConstructBinaryTreeFromPreorderInorder().buildTree(preorder, inorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length == 1)
			return new TreeNode(preorder[0]);
		TreeNode root = makeTree(preorder, inorder);
		return root;
    }
	
	private TreeNode makeTree(int[] preorder, int[] inorder) {
		if(inorder.length == 0 || preorder.length == 0) {
			return null;
		}
		if(preorder.length == 1) {
			return new TreeNode(preorder[0]);
		}
		if(inorder.length == 1) {
			return new TreeNode(inorder[0]);
		}
		TreeNode root = new TreeNode(preorder[0]);
		int rootIdx = getRootIdx(inorder, preorder[0]);
		root.left = makeTree(Arrays.copyOfRange(preorder, 1, rootIdx + 1), Arrays.copyOfRange(inorder, 0, rootIdx));
		root.right = makeTree(Arrays.copyOfRange(preorder, rootIdx + 1, preorder.length), Arrays.copyOfRange(inorder, rootIdx + 1, inorder.length));
		return root;
	}
	
	private int getRootIdx(int[] inorder, int val) {
		int idx = 0;
		for(int i = 0; i < inorder.length; i++) {
			if(inorder[i] == val) {
				idx = i;
				break;
			}
		}
		return idx;
	}
}