package aq.gym.contests.tree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {

	public static void main(String[] args) {
		int n = 3;
		List<TreeNode> trees = new UniqueBinarySearchTreesII().generateTrees(n);
		System.out.println(trees);
	}

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> trees = generateTrees(1, n);
        return trees;
    }
    
    private List<TreeNode> generateTrees(int start, int end) {
    	List<TreeNode> trees = new ArrayList<>();
    	if(start > end) {
    		trees.add(null);
    		return trees;
    	}
    	for(int i = start; i <= end; i++) {
    		List<TreeNode> leftSubTree = generateTrees(start, i - 1);
    		List<TreeNode> rightSubTree = generateTrees(i + 1, end);
    		for(TreeNode left : leftSubTree) {
    			for(TreeNode right : rightSubTree) {
    				TreeNode root = new TreeNode(i, left, right);
    				trees.add(root);
    			}
    		}
    	}
    	return trees;
    }
}
