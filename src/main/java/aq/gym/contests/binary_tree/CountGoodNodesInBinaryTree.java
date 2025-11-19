package aq.gym.contests.binary_tree;

public class CountGoodNodesInBinaryTree {

//	https://leetcode.com/problems/count-good-nodes-in-binary-tree
	public static void main(String[] args) {
//		Case #1 // 1
//		TreeNode root = new TreeNode(3);
//		System.out.println(new CountGoodNodesInBinaryTree().goodNodes(root));
		
//		Case #2 // 4
//		TreeNode root = new TreeNode(3);
//		TreeNode n1_lvl1_left = new TreeNode(1);
//		TreeNode n4_lvl1_right = new TreeNode(4);
//		root.left = n1_lvl1_left; root.right = n4_lvl1_right;
//		TreeNode n3_lvl2_left_1 = new TreeNode(3);
//		TreeNode n1_lvl2_left_2 = new TreeNode(1);
//		TreeNode n5_lvl2_right_2 = new TreeNode(5);
//		n1_lvl1_left.left = n3_lvl2_left_1; 
//		n4_lvl1_right.left = n1_lvl2_left_2; n4_lvl1_right.right = n5_lvl2_right_2; 
//		System.out.println(new CountGoodNodesInBinaryTree().goodNodes(root));
		
//		Case #3 // 3
		TreeNode root = new TreeNode(3);
		TreeNode n3_lvl1_left = new TreeNode(3);
		root.left = n3_lvl1_left;
		TreeNode n4_lvl2_left = new TreeNode(4);
		TreeNode n2_lvl2_right = new TreeNode(2);
		n3_lvl1_left.left = n4_lvl2_left; n3_lvl1_left.right = n2_lvl2_right;
		System.out.println(new CountGoodNodesInBinaryTree().goodNodes(root));
	}

    public int goodNodes(TreeNode root) {
        int[] goodNodesCount = {0};
        searchGoodNodesCount(root, root.val, goodNodesCount);
        return goodNodesCount[0];
    }
    
    private void searchGoodNodesCount(TreeNode node, int currMaxValueNode, int[] goodNodesCount) {
    	if(node == null) {
    		return;
    	}
    	 if(node.val >= currMaxValueNode) {
             goodNodesCount[0]++;
             currMaxValueNode = node.val; 
         }
    	searchGoodNodesCount(node.right, currMaxValueNode, goodNodesCount);
    	searchGoodNodesCount(node.left, currMaxValueNode, goodNodesCount);
    }
}
