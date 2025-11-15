package aq.gym.contests.binary_tree;

public class PopulatingNextRightNodes {

	public static void main(String[] args) {
		test1();
	}
	
	private static void test1() {
		Node root = new Node(3);
		Node n1 = new Node(5);
		Node n2 = new Node(7);
		Node n3 = new Node(4);
		Node n4 = new Node(8);
		Node n5 = new Node(15);
		Node n6 = new Node(20);
		root.left = n1; root.right = n2;
		n1.left = n3; n1.right = n4;
		n2.left = n5; n2.right = n6;
		Node result = new PopulatingNextRightNodes().connect(root);
		while(result != null) {
			System.out.print(result + " ");
			result = result.next;
		}
	}

	public Node connect(Node root) {
		if(root == null)
			return null;
		connect0(root.left, root.right);
		return root;
    }
	
	private void connect0(Node left, Node right) {
		if(left == null && right == null)
			return;
		left.next = right;
		connect0(left.left, left.right);
		connect0(right.left, right.right);
		connect0(left.right, right.left);
	}
}
