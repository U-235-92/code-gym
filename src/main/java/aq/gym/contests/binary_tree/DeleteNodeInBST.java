package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DeleteNodeInBST {

//	https://leetcode.com/problems/delete-node-in-a-bst
	public static void main(String[] args) {
//		Case #1
//		TreeNode root = new TreeNode(1);
//		TreeNode result = new DeleteNodeInBST().deleteNode(root, 1);
//		traverse(result);
		
//		Case #2
//		TreeNode root = new TreeNode(2);
//		root.left = new TreeNode(1);
//		root.right = new TreeNode(3);
//		TreeNode result = new DeleteNodeInBST().deleteNode(root, 2);
//		traverse(result);
		
//		Case #3
//		TreeNode root = new TreeNode(10);
//		TreeNode n6 = new TreeNode(6);
//		TreeNode n16 = new TreeNode(16);
//		root.left = n6;
//		root.right = n16;
//		TreeNode n2 = new TreeNode(2);
//		TreeNode n8 = new TreeNode(8);
//		n6.left = n2; n6.right = n8;
//		TreeNode n15 = new TreeNode(15);
//		TreeNode n17 = new TreeNode(17);
//		n16.left = n15; n16.right = n17;
//		TreeNode n7 = new TreeNode(7);
//		TreeNode n9 = new TreeNode(9);
//		n8.left = n7; n8.right = n9;
//		TreeNode result = new DeleteNodeInBST().deleteNode(root, 99);
//		traverse(result);
		
//		Case $4
//		TreeNode root = null;
//		TreeNode result = new DeleteNodeInBST().deleteNode(root, 99);
//		traverse(result);
		
//		Case #5
		TreeNode root = new TreeNode(10);
		TreeNode n5 = new TreeNode(5);
		root.left = n5;
		TreeNode n7 = new TreeNode(7);
		n5.right = n7;
		TreeNode result = new DeleteNodeInBST().deleteNode(root, 10);
		traverse(result);
	}

	private static void traverse(TreeNode node) {
		if(node != null) {			
			List<List<TreeNode>> levels = getTreeLevels(node);
			levels.forEach(System.out::println);
		} 
	}
	
	private static List<List<TreeNode>> getTreeLevels(TreeNode root) {
		List<List<TreeNode>> traverse = new ArrayList<>();
		List<TreeNode> nextLevel = new ArrayList<>();
		Queue<TreeNode> queue = new ArrayDeque<>();
		Queue<TreeNode> tmp = new ArrayDeque<>();
		queue.offer(root);
		traverse.add(List.of(root));
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if(node.left != null) {
				nextLevel.add(node.left);
				tmp.offer(node.left);
			}
			if(node.right != null) {				
				nextLevel.add(node.right);
				tmp.offer(node.right);
			}
			if(queue.isEmpty()) {
				if(!nextLevel.isEmpty()) {					
					traverse.add(nextLevel);
					queue = tmp;
					nextLevel = new ArrayList<>();
					tmp = new ArrayDeque<>();
				}
			}
		}
		return traverse;
	}
	
    public TreeNode deleteNode(TreeNode root, int key) {
    	if(root == null) {
    		return null;
    	}
    	if(isRemoveNodeRoot(root, key)) {
    		root = deleteRootNode(root);
    	} else {    		
    		deleteNoRootNode(null, root, key, new TreeNode[2]);
    	}
    	return root;
    }
    
    private TreeNode deleteRootNode(TreeNode root) {
    	if(root.left == null && root.right == null) {
			root = null;
		} else {
			if(root.right != null && root.left == null) {
				root = root.right;
			} 
			if(root.right == null && root.left != null) {
				root = root.left;
			}
			if(root.right != null && root.left != null) {
				TreeNode orphan = root.left;
				root = root.right;
				putOrphanNodeOnNewPosition(root, orphan);
			}
		}
    	return root;
    }
    
    private void deleteNoRootNode(TreeNode prev, TreeNode curr, int key, TreeNode[] data) {
    	if(curr == null) {
    		return;
    	}
    	if(curr.val == key) {
    		data[0] = prev; // parent
			data[1] = curr; // child
			deleteNode(data);
    		return;
    	}
    	if(curr.val < key) {    		
    		deleteNoRootNode(curr, curr.right, key, data);
    	} else {    		
    		deleteNoRootNode(curr, curr.left, key, data);
    	}
    }
    
//  data[0] = parent, data[1] = child
    private void deleteNode(TreeNode[] data) {
    	if(isRemoveNodeLeaf(data)) { 
    		TreeNode parent = data[0];
    		TreeNode removedChild = data[1];
    		if(isRightChild(parent, removedChild)) {
    			parent.right = null;
    		} else {
    			parent.left = null;
    		}
    		return;
    	}
    	if(hasRemoveNodeRightChildOnly(data)) {
    		TreeNode parent = data[0];
    		TreeNode removedChild = data[1];
    		if(isRightChild(parent, removedChild)) {
    			parent.right = removedChild.right;
    		} else {
    			parent.left = removedChild.right;
    		}
    		return;
    	}
    	if(hasRemoveNodeLeftChildOnly(data)) {
    		TreeNode parent = data[0];
    		TreeNode removedChild = data[1];
    		if(isRightChild(parent, removedChild)) {
    			parent.right = removedChild.left;
    		} else {
    			parent.left = removedChild.left;
    		}
    		return;
    	}
    	if(hasRemoveNodeBothChild(data)) {  
    		TreeNode parent = data[0];
    		TreeNode removedChild = data[1];
    		if(isRightChild(parent, removedChild)) {
    			TreeNode orphan = removedChild.left;
    			parent.right = removedChild.right;
    			putOrphanNodeOnNewPosition(parent.right, orphan);
    		} else {
    			TreeNode orphan = removedChild.left;
    			parent.left = removedChild.right;
    			putOrphanNodeOnNewPosition(parent.left, orphan);
    		}
    		return;
    	}
    }
    
    private boolean isRemoveNodeRoot(TreeNode node, int key) {
    	return node.val == key;
    }
    
    private boolean isRemoveNodeLeaf(TreeNode[] data) {
    	return data[0] != null && data[1].left == null && data[1].right == null;
    }
    
    private boolean hasRemoveNodeRightChildOnly(TreeNode[] data) {
    	return data[0] != null && data[1].left == null && data[1].right != null;
    }
    
    private boolean hasRemoveNodeLeftChildOnly(TreeNode[] data) {
    	return data[0] != null && data[1].left != null && data[1].right == null;
    }
    
    private boolean hasRemoveNodeBothChild(TreeNode[] data) {
    	return data[0] != null && data[1].left != null && data[1].right != null;
    }
    
    private boolean isRightChild(TreeNode parent, TreeNode child) {
    	return parent.right == child;
    }
    
    private void putOrphanNodeOnNewPosition(TreeNode node, TreeNode orphan) {
    	if(node == null) {
    		return;
    	}
    	if(orphan.val < node.val) {
    		if(node.left == null) {
    			node.left = orphan;
    			return;
    		} else {    			
    			putOrphanNodeOnNewPosition(node.left, orphan);
    		}
    	} else {
    		if(node.right == null) {
    			node.right = orphan;
    			return;
    		} else {    			
    			putOrphanNodeOnNewPosition(node.right, orphan);
    		}
    	}
    }
}
