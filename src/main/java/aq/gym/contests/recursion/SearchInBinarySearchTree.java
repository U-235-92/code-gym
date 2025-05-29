package aq.gym.contests.recursion;

public class SearchInBinarySearchTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(7);
		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(3);
		root.left = n2; root.right = n3;
		n2.left = n4; n2.right = n5;
		int val = 4;
		TreeNode result = new SearchInBinarySearchTree().searchBST(root, val);
		print(result);
	}

    public TreeNode searchBST(TreeNode root, int val) {
        if(val == root.val)
        	return root;
        TreeNode[] search = new TreeNode[1];
        search(search, root, val);
        return search[0];
    }
    
    private void search(TreeNode[] search, TreeNode node, int val) {
    	if(node == null) {
    		return;
    	}
    	if(node.left == null && node.right == null) {
    		if(node.val == val) {
    			search[0] = node;
    		}
    		return;    		
    	}
    	if(node.val == val) {
    		search[0] = node;
    		return;
    	}
    	search(search, node.left, val);
    	search(search, node.right, val);
    }

    private static void print(TreeNode node) {
    	if(node == null) {
    		System.out.println("There are no any nodes!");
    		return;
    	}
    	if(node.left == null && node.right == null) {
    		System.out.print(node + " ");
    		return;
    	}
    	System.out.print(node + " ");
		print(node.left);
		print(node.right);
    }
    
	public static class TreeNode {
		private int val;
		private TreeNode left;
		private TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return "" + val;
		}
	}
}
