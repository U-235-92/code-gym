package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class BinaryTreeUtil {

	protected static void printByLevel(TreeNode root) {
		List<List<TreeNode>> levelOrderTraverse = new LinkedList<>();
		Queue<TreeNode> nextLevelNodes = new ArrayDeque<>();
		Queue<TreeNode> currLevelNodes = new ArrayDeque<>();
		currLevelNodes.offer(root);
		levelOrderTraverse.add(List.of(root));
		while(!currLevelNodes.isEmpty()) {
			TreeNode node = currLevelNodes.poll();
			if(node.left != null) {
				nextLevelNodes.add(node.left);
			}
			if(node.right != null) {
				nextLevelNodes.add(node.right);
			}
			if(currLevelNodes.isEmpty()) {
				currLevelNodes = nextLevelNodes;
				nextLevelNodes = new ArrayDeque<>();
				if(!currLevelNodes.isEmpty()) {					
					levelOrderTraverse.add(new ArrayList<>(currLevelNodes));
				}
			}
		}
		levelOrderTraverse.forEach(System.out::println);
	}
	
	protected static void printPathsFromRootToLeaf(TreeNode root) {
		List<Deque<TreeNode>> list = new ArrayList<Deque<TreeNode>>();
		evaluateAllUniquePaths(root, list, new ArrayDeque<TreeNode>());
		list.forEach(System.out::println);
	}
	
    private static void evaluateAllUniquePaths(TreeNode node, List<Deque<TreeNode>> paths, Deque<TreeNode> currPath) {
    	if(node == null) {
    		return;
    	}
    	currPath.offer(node);
    	if(node.left == null && node.right == null) {
    		paths.add(new ArrayDeque<TreeNode>(currPath));
    	}
    	evaluateAllUniquePaths(node.left, paths, currPath);
    	evaluateAllUniquePaths(node.right, paths, currPath);
    	currPath.removeLast();
    }
    
    protected static void printPathFromRootToNode(TreeNode root, TreeNode target) {
    	List<Deque<TreeNode>> list = new ArrayList<Deque<TreeNode>>();
    	evaluateTargetNodePath(root, list, new ArrayDeque<TreeNode>(), target);
		list.forEach(System.out::println);
    }
    
    private static void evaluateTargetNodePath(TreeNode node, List<Deque<TreeNode>> paths, Deque<TreeNode> currPath, TreeNode target) {
    	if(node == null) {
    		return;
    	}
    	currPath.offer(node);
    	if(node.val == target.val) {
    		paths.add(new ArrayDeque<TreeNode>(currPath));
    		return;
    	}
    	evaluateTargetNodePath(node.left, paths, currPath, target);
    	evaluateTargetNodePath(node.right, paths, currPath, target);
    	currPath.removeLast();
    }
}
