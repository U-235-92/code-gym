package aq.gym.algorithms_and_structures.tree.simple_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {

	private int value;
	private Tree left;
	private Tree right;
	
	public Tree(int value) {
		this.value = value;
	}
	
	public void insertRight(int value) {
		if(right == null) {
			Tree subTree = new Tree(value);
			this.right = subTree;
		} else {
			if(right != null) {
				right.insertRight(value);
			}
		}
	}
	
	public void insetLeft(int value) {
		if(left == null) {
			Tree subTree = new Tree(value);
			this.left = subTree;
		} else {
			if(left != null)
				left.insetLeft(value);
		}
	}
	
	public void inverse() {
		Queue<Tree> trees = new LinkedList<>();
		trees.add(this);
		while(trees.size() > 0) {
			Tree tree = trees.remove();
			Tree tmp = tree.left;
			tree.left = tree.right;
			tree.right = tmp;
			if(tree.right != null) {
				trees.add(tree.right);
			} 
			if(tree.left != null) {
				trees.add(tree.left);
			} 
		}
	}
	
	public void breadthWalk() {
		Queue<Tree> treeQueue = new LinkedList<Tree>();
		treeQueue.add(this);
		while(treeQueue.size() > 0) {
			Tree tree = treeQueue.remove();
			System.out.print(tree + " ");
			if(tree.right != null) {
				treeQueue.add(tree.right);
			} 
			if(tree.left != null) {
				treeQueue.add(tree.left);
			} 
		}
	}
	
	public void deepPreOrderWalk() {
		if(this != null) {
			System.out.print(this + " ");
			if(this.left != null) {				
				this.left.deepPreOrderWalk();
			}
			if(this.right != null) {				
				this.right.deepPreOrderWalk();
			}
		}
	}
	
	public void deepPostOrderWalk() {
		if(this != null) {
			if(this.left != null) {				
				this.left.deepPostOrderWalk();
			}
			if(this.right != null) {				
				this.right.deepPostOrderWalk();
			}
			System.out.print(this + " ");
		}
	}
	
	public void deepInOrderWalk() {
		if(this != null) {
			if(this.left != null) {				
				this.left.deepInOrderWalk();
			}
			System.out.print(this + " ");
			if(this.right != null) {				
				this.right.deepInOrderWalk();
			}
		}
	}
	
	@Override
	public String toString() {
		return value + "";
	}
}
