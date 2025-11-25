package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class LowestCommonAncestorOfBinaryTree {

//	https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
	public static void main(String[] args) {
//		TreeNode root = new TreeNode(1, new TreeNode(2), null);
//		TreeNode p = new TreeNode(1), q = new TreeNode(2);
//		System.out.println(new LowestCommonAncestorOfBinaryTree().lowestCommonAncestor(root, p, q));
		
		TreeNode root = new TreeNode(3);
		TreeNode n5 = new TreeNode(5);
		TreeNode n1 = new TreeNode(1);
		root.left = n5; root.right = n1;
		TreeNode n6 = new TreeNode(6);
		TreeNode n2 = new TreeNode(2);
		n5.left = n6; n5.right = n2;
		TreeNode n0 = new TreeNode(0);
		TreeNode n8 = new TreeNode(8);
		n1.left = n0; n1.right = n8;
		TreeNode n7 = new TreeNode(7);
		TreeNode n4 = new TreeNode(4);
		n2.left = n7; n2.right = n4;
		TreeNode p = new TreeNode(4);
		TreeNode q = new TreeNode(0);
		System.out.println(new LowestCommonAncestorOfBinaryTree().lowestCommonAncestor(root, p, q));
	}

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	Queue<TreeNode> pAncestors = getAncestorsOf(p, root);
    	Queue<TreeNode> qAncestors = getAncestorsOf(q, root);
    	TreeNode ancestor = getCommonAncestorOf(pAncestors, qAncestors);
    	return ancestor;
    }
    
    private Queue<TreeNode> getAncestorsOf(TreeNode descendand, TreeNode node) {
    	List<Deque<TreeNode>> list = new ArrayList<Deque<TreeNode>>();
    	evaluateAncestorsOf(descendand, node, list, new ArrayDeque<TreeNode>());
		return list.remove(0);
    }
    
    private void evaluateAncestorsOf(TreeNode descendant, TreeNode node, List<Deque<TreeNode>> paths, Deque<TreeNode> path) {
    	if(node == null) {
    		return;
    	}
    	path.offer(node);
    	if(node.val == descendant.val) {
    		paths.add(new ArrayDeque<TreeNode>(path));
    		return;
    	}
    	evaluateAncestorsOf(descendant, node.left, paths, path);
    	evaluateAncestorsOf(descendant, node.right, paths, path);
    	path.removeLast();
    }
    
    private TreeNode getCommonAncestorOf(Queue<TreeNode> pAncestors, Queue<TreeNode> qAncestors) {
    	TreeNode ancestor = null;
    	while(!pAncestors.isEmpty() && !qAncestors.isEmpty()) {
    		TreeNode pAncestor = pAncestors.poll();
    		TreeNode qAncestor = qAncestors.poll();
    		if(pAncestor.val == qAncestor.val) {
    			ancestor = qAncestor;
    		} else {
    			break;
    		}
    	}
    	return ancestor;
    }
}
