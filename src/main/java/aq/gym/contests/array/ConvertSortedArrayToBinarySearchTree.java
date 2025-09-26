package aq.gym.contests.array;

import java.util.Arrays;

public class ConvertSortedArrayToBinarySearchTree {

	public static void main(String[] args) {
		int[] nums = {-10,-3,0,5,9};
		ConvertSortedArrayToBinarySearchTree converter = new ConvertSortedArrayToBinarySearchTree();
		TreeNode tree = converter.sortedArrayToBST(nums);
		converter.traverse(tree);
		
	}
	
    public TreeNode sortedArrayToBST(int[] nums) {
    	if(nums.length == 1) {
    		return new TreeNode(nums[0]); 
    	}
    	if(nums.length == 0) {
    		return null;
    	}
    	int val = nums[nums.length / 2];
        TreeNode left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, nums.length / 2));
        TreeNode right = sortedArrayToBST(Arrays.copyOfRange(nums, (nums.length / 2) + 1, nums.length));
        TreeNode root = new TreeNode(val, left, right);
        return root;
    }
	
    private void traverse(TreeNode node) {
    	if(node == null) {    		
    		System.out.print("[null]");
    		return;
    	}
    	traverse(node.left);
    	System.out.print("[" + node.val + "]");
    	traverse(node.right);
    }
    
	private class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      @SuppressWarnings("unused")
		  TreeNode() {}
	      TreeNode(int val) { this.val = val; }
	      TreeNode(int val, TreeNode left, TreeNode right) {
	          this.val = val;
	          this.left = left;
	          this.right = right;
	      }
	 }
}
