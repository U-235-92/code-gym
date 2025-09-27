package aq.gym.contests.tree;

import java.util.ArrayList;
import java.util.List;

public class SameTree {

	public static void main(String[] args) {
		TreeNode p = new TreeNode(1, new TreeNode(2), null);
		TreeNode q = new TreeNode(1, null, new TreeNode(2));
		System.out.println(new SameTree().isSameTree(p, q));
	}

    public boolean isSameTree(TreeNode p, TreeNode q) {
    	List<Integer> pVals = new ArrayList<>();
    	List<Integer> qVals = new ArrayList<>();
    	fillListNodes(p, pVals);
    	fillListNodes(q, qVals);
    	return pVals.equals(qVals);
    }
    
    private void fillListNodes(TreeNode node, List<Integer> nodes) {
    	if(node == null) {
    		nodes.add(null);
    		return;
    	}
    	fillListNodes(node.left, nodes);
    	fillListNodes(node.right, nodes);
    	nodes.add(node.val);
    }
}
