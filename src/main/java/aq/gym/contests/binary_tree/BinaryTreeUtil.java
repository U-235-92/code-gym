package aq.gym.contests.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class BinaryTreeUtil {

	protected static void printByLevel(TreeNode root) {
		List<List<TreeNode>> levelOrderTraverse = new LinkedList<>();
		Queue<TreeNode> nextLevelNodes = new ArrayDeque<>();
		Queue<TreeNode> currLevelNodes = new ArrayDeque<>();
		currLevelNodes.offer(root);
		levelOrderTraverse.add(List.of(root));
		while(!currLevelNodes.isEmpty()) {
			TreeNode node = currLevelNodes.poll();
			if(node.left != null) {
				nextLevelNodes.add(node.left);
			}
			if(node.right != null) {
				nextLevelNodes.add(node.right);
			}
			if(currLevelNodes.isEmpty()) {
				currLevelNodes = nextLevelNodes;
				nextLevelNodes = new ArrayDeque<>();
				if(!currLevelNodes.isEmpty()) {					
					levelOrderTraverse.add(new ArrayList<>(currLevelNodes));
				}
			}
		}
		levelOrderTraverse.forEach(System.out::println);
	}
}
