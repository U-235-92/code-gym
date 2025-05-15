package aq.gym.contests.tree;

import java.util.Arrays;

public class ConstructBinaryTreeFromInorderPostorder {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
	}

	private static void test1() {
		int[] inorder = {9,3,15,20,7};
		int[] postorder = {9,15,7,20,3};
		TreeNode root = new ConstructBinaryTreeFromInorderPostorder().buildTree(inorder, postorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test2() {
		int[] inorder = {5,9,3};
		int[] postorder = {5,9,3};
		TreeNode root = new ConstructBinaryTreeFromInorderPostorder().buildTree(inorder, postorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test3() {
		int[] inorder = {3,9,5};
		int[] postorder = {5,9,3};
		TreeNode root = new ConstructBinaryTreeFromInorderPostorder().buildTree(inorder, postorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test4() {
		int[] inorder = {2,4,5,8,3,15,7,20};
		int[] postorder = {2,4,8,5,15,20,7,3};
		TreeNode root = new ConstructBinaryTreeFromInorderPostorder().buildTree(inorder, postorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test5() {
		int[] inorder = {5};
		int[] postorder = {5};
		TreeNode root = new ConstructBinaryTreeFromInorderPostorder().buildTree(inorder, postorder);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
//	Construct Binary Tree from Inorder and Postorder Traversal
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(inorder.length == 1) 
			return new TreeNode(inorder[0]);
		return makeTree(inorder, postorder);
    }
	
	private TreeNode makeTree(int[] inorder, int[] postorder) {
		if(inorder.length <= 0) 
			return null;
		if(postorder.length <= 0) 
			return null;
		if(inorder.length == 1) 
			return new TreeNode(inorder[0]);
		if(postorder.length == 1) 
			return new TreeNode(postorder[0]);
		TreeNode root = new TreeNode(postorder[postorder.length - 1]);
		int divIdx = getDivisionIndex(inorder, postorder[postorder.length - 1]);
		root.left = makeTree(Arrays.copyOfRange(inorder, 0, divIdx), Arrays.copyOfRange(postorder, 0, divIdx));
		root.right = makeTree(Arrays.copyOfRange(inorder, divIdx + 1, inorder.length), Arrays.copyOfRange(postorder, divIdx, postorder.length - 1));
        return root;
	}
	
	private int getDivisionIndex(int[] inorder, int val) {
		int divIdx = 0;
		for(int i = 0; i < inorder.length; i++) {
			if(inorder[i] == val) {
				divIdx = i;
				break;
			}
		}
		return divIdx;
	}
}
