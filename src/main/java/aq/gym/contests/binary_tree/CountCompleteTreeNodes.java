package aq.gym.contests.binary_tree;

public class CountCompleteTreeNodes {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.left = n2; root.right = n3;
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n2.left = n4; n2.right = n5;
		TreeNode n6 = new TreeNode(6);
		n3.left = n6;
		System.out.println(new CountCompleteTreeNodes().countNodes(root));
	}

    public int countNodes(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
        int[] accumulator = {0};
        countNodes(root, accumulator);
        return accumulator[0];
    }
    
    private void countNodes(TreeNode node, int[] accumulator) {
    	if(node == null) {
    		return;
    	}
    	accumulator[0]++;
    	countNodes(node.left, accumulator);
    	countNodes(node.right, accumulator);
    }
}
