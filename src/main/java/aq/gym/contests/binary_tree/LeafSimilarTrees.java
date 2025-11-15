package aq.gym.contests.binary_tree;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {

//	https://leetcode.com/problems/leaf-similar-trees/description
	public static void main(String[] args) {
		
	}

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    	List<Integer> leafes1 = new ArrayList<Integer>();
    	List<Integer> leafes2 = new ArrayList<Integer>();
        accumulateLeafes(root1, leafes1);
        accumulateLeafes(root2, leafes2);
        return leafes1.equals(leafes2);
    }
    
    private void accumulateLeafes(TreeNode node, List<Integer> accumulator) {
    	if(node == null) {
    		return;
    	}
    	if(node.left == null && node.right == null) {
    		accumulator.add(node.val);
    		return;
    	}
    	accumulateLeafes(node.left, accumulator);
    	accumulateLeafes(node.right, accumulator);
    }
}
