package aq.gym.algorithms_and_structures.tree.search_binary_tree;

public class Tree {

private Node root;
	
	public Tree() {}
	
	public Tree(Node node) {
		root = node;
	}
	
	public void add(Node node) {
		if(root == null) {
			root = node;
		} else {
			Node current = root;
			Node parent = null;
			while(true) {
				parent = current;
				if(node.getIntData() > current.getIntData()) {
					current = current.getRightChild();
					if(current == null) {
						parent.setRightChild(node);
						return;
					}
				} else {
					current = current.getLeftChild();
					if(current == null) {
						parent.setLeftChild(node);
						return;
					}
				}
			}
		}
	}
	
	public Node remove(int val) {
		Node removeNode = root;
		Node parentRemove = root;
		boolean isLeftTurn = false;
		while(removeNode != null && removeNode.getIntData() != val) {
			parentRemove = removeNode;
			if(val > removeNode.getIntData()) {
				removeNode = removeNode.getRightChild();
				isLeftTurn = false;
			} else {
				removeNode = removeNode.getLeftChild();
				isLeftTurn = true;
			}
		}
		if(removeNode != null) {
			if(isLeaf(removeNode)) {
				if(isRootNode(removeNode)) {
					root = null;
					return removeNode;
				} else {
					if(isLeftTurn) {
						parentRemove.setLeftChild(null);
						return removeNode;
					} else {
						parentRemove.setRightChild(null);
						return removeNode;
					}
				}	
			} else if(hasOnlyLeftChild(removeNode)) {
				if(isRootNode(removeNode)) {
					root = removeNode.getLeftChild();
					return removeNode;
				} else {
					if(isLeftTurn) {
						parentRemove.setLeftChild(removeNode.getLeftChild());
						return removeNode;
					} else {
						parentRemove.setRightChild(removeNode.getLeftChild());
						return removeNode;
					}
				}
			} else if(hasOnlyRightChild(removeNode)) {
				if(isRootNode(removeNode)) {
					root = removeNode.getRightChild();
					return removeNode;
				} else {
					if(isLeftTurn) {
						parentRemove.setLeftChild(removeNode.getRightChild());
						return removeNode;
					} else {
						parentRemove.setRightChild(removeNode.getRightChild());
						return removeNode;
					}
				}
			} else if(hasTwoChild(removeNode)) {
				if(isRootNode(removeNode)) {
					Node successor = getSuccessor(removeNode);
					if(isLeaf(successor)) {
						successor.setLeftChild(root.getLeftChild());
						successor.setRightChild(root.getRightChild());
						root = successor;
						Node parentSuccessor = getParentSuccessor(successor, removeNode);
						parentSuccessor.setLeftChild(null);
						return removeNode;
					} else {
						Node parentSuccessor = getParentSuccessor(successor, removeNode);
						parentSuccessor.setLeftChild(successor.getRightChild());
						successor.setLeftChild(root.getLeftChild());
						successor.setRightChild(root.getRightChild());
						root = successor;
						return removeNode;
					}
				} else {
					Node successor = getSuccessor(removeNode);
					if(isLeaf(successor)) {
						if(isLeftTurn) {
							Node parentSuccessor = getParentSuccessor(successor, removeNode);
							if(parentSuccessor == removeNode) {
								Node leftChild = removeNode.getLeftChild();
								parentRemove.setLeftChild(successor);
								successor.setLeftChild(leftChild);
								return removeNode;
							} else {
								Node leftChild = removeNode.getLeftChild();
								Node rightChild = removeNode.getRightChild();
								parentRemove.setLeftChild(successor);
								successor.setLeftChild(leftChild);
								successor.setRightChild(rightChild);
								parentSuccessor.setLeftChild(null);
								return removeNode;
							}
						} else {
							Node parentSuccessor = getParentSuccessor(successor, removeNode);
							if(parentSuccessor == removeNode) {
								Node leftChild = removeNode.getLeftChild();
								parentRemove.setRightChild(successor);
								successor.setLeftChild(leftChild);
								return removeNode;
							} else {
								Node leftChild = removeNode.getLeftChild();
								Node rightChild = removeNode.getRightChild();
								parentRemove.setRightChild(successor);
								successor.setLeftChild(leftChild);
								successor.setRightChild(rightChild);
								parentSuccessor.setLeftChild(null);
								return removeNode;
							}
						}
					} else {
						if(isLeftTurn) {
							Node parentSuccessor = getParentSuccessor(successor, removeNode);
							if(parentSuccessor == removeNode) {
								Node leftChild = removeNode.getLeftChild();
								parentRemove.setLeftChild(successor);
								successor.setLeftChild(leftChild);
								return removeNode;
							} else {
								Node leftChild = removeNode.getLeftChild();
								Node rightChild = removeNode.getRightChild();
								parentSuccessor.setLeftChild(successor.getRightChild());
								parentRemove.setLeftChild(successor);
								successor.setLeftChild(leftChild);
								successor.setRightChild(rightChild);
								return removeNode;
							}
						} else {
							Node parentSuccessor = getParentSuccessor(successor, removeNode);
							if(parentSuccessor == removeNode) {
								Node leftChild = removeNode.getLeftChild();
								parentRemove.setRightChild(successor);
								successor.setLeftChild(leftChild);
								return removeNode;
							} else {
								Node leftChild = removeNode.getLeftChild();
								Node rightChild = removeNode.getRightChild();
								parentSuccessor.setLeftChild(successor.getRightChild());
								parentRemove.setRightChild(successor);
								successor.setLeftChild(leftChild);
								successor.setRightChild(rightChild);
								return removeNode;
							}
						}
					}
				}
			}
		}
		return null; 
	}
	
	private boolean isLeaf(Node node) {
		return node.getLeftChild() == null && node.getRightChild() == null;
	}
	
	private boolean isRootNode(Node node) {
		return node == root;
	}
	
	private boolean hasOnlyLeftChild(Node node) {
		return node.getRightChild() == null && node.getLeftChild() != null;
	}
	
	private boolean hasOnlyRightChild(Node node) {
		return node.getRightChild() != null && node.getLeftChild() == null;
	}
	
	private boolean hasTwoChild(Node node) {
		return node.getLeftChild() != null && node.getRightChild() != null;
	}
	
	private Node getSuccessor(Node node) {
		Node successor = null;
		Node current = node.getRightChild();
		if(current.getLeftChild() != null) {
			while(current != null) {
				successor = current;
				current = current.getLeftChild();
			}
			return successor;
		} else {
			successor = current;
			return successor;
		}
	}
	
	private Node getParentSuccessor(Node successor, Node removeNode) {
		Node parentSuccessor = removeNode;
		Node current = removeNode.getRightChild();
		while(current != successor) {
			parentSuccessor = current;
			current = current.getLeftChild();
		}
		return parentSuccessor;
	}
	
	public Node min() {
		Node min = null;
		Node current = root;
		while(current != null) {
			min = current;
			current = current.getLeftChild();
		}
		return min;
	}
	
	public Node max() {
		Node max = null;
		Node current = root;
		while(current != null) {
			max = current;
			current = current.getRightChild();
		}
		return max;
	}
	
	public void travel() {
		travel0(root);
	}
	
	private void travel0(Node node) {
		if(node != null) {
			System.out.println(node);
			travel0(node.getLeftChild());
			travel0(node.getRightChild());
		}
	}
	
	public void print() {
		print0(root, 10);
	}
	
	private void print0(Node node, int i) {
		System.out.println();
		int p = i;
		if(node != null) {
			while(p-- > 0) {
				System.out.print(" ");
			}
			System.out.print(node);
			p = i;
			print0(node.getLeftChild(), p--);
			print0(node.getRightChild(), p--);
		}
	}
}
