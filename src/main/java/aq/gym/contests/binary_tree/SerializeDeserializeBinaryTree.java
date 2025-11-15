package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unused")
public class SerializeDeserializeBinaryTree {

	private static final String TREE_LINE_DELIMITER = ",";
	private static final String TREE_LEVEL_DELIMITER = " ";
	private static final String NULL_NODE_VALUE = "#";

	public static void main(String[] args) {
		TreeNode root = getRoot1();
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
		String encoded = new SerializeDeserializeBinaryTree().serialize(root);
		System.out.println(encoded);
		TreeNode decoded = new SerializeDeserializeBinaryTree().deserialize(encoded);
		System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(decoded));
	}

	private static TreeNode getRoot1() {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(5);
		TreeNode n5 = new TreeNode(8);
		root.left = n1; root.right = n2;
		n2.left = n3; n2.right = n4;
//		n3.left = n5;
		return root;
	}
	
	private static TreeNode getRoot2() {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(5);
		root.left = n1; root.right = n3;
		n1.left = n2; n3.right = n4;
		return root;
	}
	
	private static TreeNode getRoot3() {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		root.right = n1;
		return root;
	}
	
	private static TreeNode getRoot4() {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(5);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(3);
		TreeNode n7 = new TreeNode(8);
		TreeNode n8 = new TreeNode(8);
		root.left = n1; root.right = n2;
		n1.left = n3; n1.right = n4;
		n2.left = n5; n2.right = n6;
		n3.left = n7;
		n5.right = n8;
		return root;
	}
	
	public String serialize(TreeNode root) {
		if(root == null)
			return "";
		StringBuilder encoded = new StringBuilder();
		StringBuilder line = new StringBuilder();
        Queue<TreeNode> currLineQueue = new LinkedList<>();
        Queue<TreeNode> nextLineQueue = new LinkedList<>();
        currLineQueue.add(root);
        while(!currLineQueue.isEmpty()) {
        	TreeNode node = currLineQueue.remove();
        	if(node == null) {        		
        		writeNullTreeNode(line);
        	} else {
        		writeNoNullTreeNode(node, nextLineQueue, line);
        	}
        	if(currLineQueue.isEmpty()) {
        		if(!nextLineQueue.isEmpty()) {        			
        			writeLine(encoded, line);
        			currLineQueue = nextLineQueue;
        			nextLineQueue = new LinkedList<>();
        			line = new StringBuilder();
        		}
        	}
        }
        return encoded.toString().trim();
    }

	private void writeNullTreeNode(StringBuilder line) {
		line.append(NULL_NODE_VALUE);
		line.append(TREE_LINE_DELIMITER);
	}
	
	private void writeNoNullTreeNode(TreeNode node, Queue<TreeNode> nextLineQueue, StringBuilder line) {
		line.append(node.val); 
    	line.append(TREE_LINE_DELIMITER);
    	if(node.left == null) {            		
    		nextLineQueue.add(null);
    	} else {            		
    		nextLineQueue.add(node.left);
    	}
    	if(node.right == null) {            		
    		nextLineQueue.add(null);
    	} else {            		
    		nextLineQueue.add(node.right);
    	}
	}
	
	private void writeLine(StringBuilder encoded, StringBuilder line) {
		encoded.append(TREE_LEVEL_DELIMITER);
		line.replace(line.length() - 1, line.length(), "");
		encoded.append(line);
	}
	
    public TreeNode deserialize(String data) {
    	if(data.equals(""))
    		return null;
    	String[] treeLines = data.split(TREE_LEVEL_DELIMITER);
    	int rootVal = Integer.valueOf(treeLines[0]);
    	TreeNode root = new TreeNode(rootVal);
    	Deque<List<TreeNode>> stack = new ArrayDeque<>();
    	stack.push(List.of(root));
    	for(int i = 1; i < treeLines.length; i++) {
    		String[] lineVals = treeLines[i].split(TREE_LINE_DELIMITER);
    		List<TreeNode> currNodesLine = stack.pop();
    		List<TreeNode> nextNodesLine = new ArrayList<>();
    		for(int n = 0, v = 0; n < currNodesLine.size() && v < lineVals.length - 1; n++, v += 2) {
    			String leftVal = lineVals[v];
    			String rightVal = lineVals[v + 1];
    			connect(currNodesLine.get(n), leftVal, rightVal, nextNodesLine);
    		}
    		stack.push(nextNodesLine);
    	}
        return root;
    }
    
    private void connect(TreeNode node, String leftVal, String rightVal, List<TreeNode> nextNodesLine) {
    	if(leftVal.equals(NULL_NODE_VALUE)) {
    		node.left = null;
    	} else {
    		TreeNode left = new TreeNode(Integer.valueOf(leftVal));
    		node.left = left;
    		nextNodesLine.add(left);
    	}
    	if(rightVal.equals(NULL_NODE_VALUE)) {
    		node.right = null;
    	} else {
    		TreeNode right = new TreeNode(Integer.valueOf(rightVal));
    		node.right = right;
    		nextNodesLine.add(right);
    	}
    }
}
