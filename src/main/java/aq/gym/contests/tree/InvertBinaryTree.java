package aq.gym.contests.tree;

public class InvertBinaryTree {

//	https://leetcode.com/problems/invert-binary-tree/description/
	public static void main(String[] args) {
//		[4,2,7,1,3,6,9]
		TreeNode root = new TreeNode(4);
		TreeNode n2 = new TreeNode(2);
		TreeNode n7 = new TreeNode(7);
		TreeNode n1 = new TreeNode(1);
		TreeNode n3 = new TreeNode(3);
		TreeNode n6 = new TreeNode(6);
		TreeNode n9 = new TreeNode(9);
		root.left = n2;
		root.right = n7;
		n2.left = n1;
		n2.right = n3;
		n7.left = n6;
		n7.right = n9;
		new InvertBinaryTree().invertTree(root);
	}

    public TreeNode invertTree(TreeNode root) {
    	if(root == null) {
    		return root;
    	}
    	invert(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    
    private void invert(TreeNode root) {
    	TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode tmp = left;
        left = right;
        right = tmp;
        root.left = left;
        root.right = right;
    }
}
