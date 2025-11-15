package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
	}

	private static void test1() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		root.left = n2; root.right = n3;
		n2.left = n4;
		n3.right = n5;
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test2() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		root.left = n2; n2.left = n3; n3.left = n4; n4.left = n5;
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test3() {
		TreeNode root = new TreeNode(3);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(20);
		TreeNode n4 = new TreeNode(15);
		TreeNode n5 = new TreeNode(7);
		root.left = n2; root.right = n3;
		n3.left = n4; n3.right = n5;
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test4() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		root.left = n2; root.right = n3;
		n2.left = n4; n2.right = n5;
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test5() {
		TreeNode root = new TreeNode(0);
		TreeNode n2 = new TreeNode(1);
		TreeNode n3 = new TreeNode(2);
		TreeNode n4 = new TreeNode(3);
		TreeNode n5 = new TreeNode(4);
		TreeNode n6 = new TreeNode(5);
		TreeNode n7 = new TreeNode(6);
		TreeNode n8 = new TreeNode(7);
		TreeNode n9 = new TreeNode(8);
		root.left = n2; root.right = n3;
		n2.left = n4; 
		n3.left = n5; n3.right = n6;
		n5.left = n8; n5.right = n9;
		n4.left = n7;
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	private static void test6() {
		TreeNode root = new TreeNode(0);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(3);
		TreeNode n6 = new TreeNode(-1);
		TreeNode n7 = new TreeNode(5);
		TreeNode n8 = new TreeNode(1);
		TreeNode n9 = new TreeNode(6);
		TreeNode n10 = new TreeNode(8);
		root.left = n2; root.right = n3;
		n2.left = n4; 
		n3.left = n5; n3.right = n6;
		n5.left = n9;
		n6.right = n10;
		n4.left = n7; n4.right = n8;
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
	}
	
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traverse = new ArrayList<>();
        if(root == null)
        	return traverse;
        Queue<TreeNode> queue = new ArrayDeque<>();
        Queue<TreeNode> tmp = new ArrayDeque<>();
        List<Integer> children = new ArrayList<>();
        queue.add(root);
        traverse.add(List.of(root.val));
        while(!queue.isEmpty()) {
        	TreeNode node = queue.remove();
        	if(node.left != null) {
        		children.add(node.left.val);
        		tmp.add(node.left);
        	}
        	if(node.right != null) {
        		children.add(node.right.val);
        		tmp.add(node.right);
        	}
        	if(queue.isEmpty()) {
        		if(children.size() > 0) {        			
        			traverse.add(children);
        			queue = tmp;
        			tmp = new ArrayDeque<>();
        			children = new ArrayList<>();
        		}
        	}
        }
        return traverse;
    }
}
