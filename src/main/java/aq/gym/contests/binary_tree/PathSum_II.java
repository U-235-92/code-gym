package aq.gym.contests.binary_tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum_II {

//	https://leetcode.com/problems/path-sum-ii/description/
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n8 = new TreeNode(8);
		root.left = n4; root.right = n8;
		TreeNode n11 = new TreeNode(11);
		n4.left = n11;
		TreeNode n13 = new TreeNode(13);
		TreeNode n44 = new TreeNode(4);
		n8.left = n13; n8.right = n44;
		TreeNode n7 = new TreeNode(7);
		TreeNode n2 = new TreeNode(2);
		n11.left = n7; n11.right = n2;
		TreeNode n5 = new TreeNode(5);
		TreeNode n1 = new TreeNode(1);
		n44.left = n5; n44.right = n1;
		List<List<Integer>> paths = new PathSum_II().pathSum(root, 22);
		paths.forEach(System.out::println);
	}

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    	if(root == null) {
    		return List.of();
    	}
    	List<List<Integer>> paths = new ArrayList<>();
    	pathSum(root, paths, new ArrayList<Integer>(), targetSum, 0);
    	return paths;
    }
    
    private void pathSum(TreeNode node, List<List<Integer>> paths, List<Integer> path, int targetSum, int currentSum) {
    	if(node == null) {
    		return;
    	}
    	path.add(node.val);
    	currentSum += node.val;
    	if(currentSum == targetSum && node.left == null && node.right == null) {
    		paths.add(new ArrayList<Integer>(path));
    	}
    	pathSum(node.left, paths, path, targetSum, currentSum);
    	pathSum(node.right, paths, path, targetSum, currentSum);
    	currentSum -= path.removeLast();
    }
}
