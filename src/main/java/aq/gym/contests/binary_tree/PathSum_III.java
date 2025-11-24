package aq.gym.contests.binary_tree;

public class PathSum_III {

//	https://leetcode.com/problems/path-sum-iii/description/
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		TreeNode pn5 = new TreeNode(5);
		TreeNode nn3 = new TreeNode(-3);
		root.left = pn5; root.right = nn3;
		TreeNode pn11 = new TreeNode(11);
		nn3.right = pn11;
		TreeNode pn3 = new TreeNode(3);
		TreeNode pn2 = new TreeNode(2);
		pn5.left = pn3; pn5.right = pn2;
		TreeNode pn4 = new TreeNode(4);
		TreeNode nn2 = new TreeNode(-2);
		pn3.left = pn4; pn3.right = nn2;
		TreeNode pn1 = new TreeNode(1);
		pn2.right = pn1;
		System.out.println(new PathSum_III().pathSum(root, 8));
//		TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5)))));
//		System.out.println(new PathSum_III().pathSum(root, 3));
	}

    public int pathSum(TreeNode root, int targetSum) {
    	if(root == null) {
    		return 0;
    	}
        int[] pathNumber = {0};
        pathSum(root, targetSum, pathNumber);
        return pathNumber[0];
    }
    
    private void pathSum(TreeNode node, int targetSum, int[] pathNumber) {
    	if(node == null) {
    		return;
    	}
        evaluatePathSum(node, targetSum, 0L, pathNumber);
        pathSum(node.left, targetSum, pathNumber);
        pathSum(node.right, targetSum, pathNumber);
    }
    
    private void evaluatePathSum(TreeNode node, int targetSum, long sum, int[] pathNumber) {
    	if(node == null) {
    		return;
    	}    
    	sum = sum + node.val;
    	if(sum == targetSum) {
    		pathNumber[0]++;
    	}
    	evaluatePathSum(node.left, targetSum, sum, pathNumber);
    	evaluatePathSum(node.right, targetSum, sum, pathNumber);
    }
}
