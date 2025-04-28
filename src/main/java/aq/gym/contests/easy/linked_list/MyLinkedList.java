package aq.gym.contests.easy.linked_list;

public class MyLinkedList {
	
	public static void main(String[] args) {
//		MyLinkedList myLinkedList = new MyLinkedList();
//		myLinkedList.addAtHead(5);
//		myLinkedList.addAtHead(8);
//		myLinkedList.addAtTail(12);
//		myLinkedList.addAtIndex(2, 58);
//		myLinkedList.deleteAtIndex(2);
//		System.out.println(myLinkedList.get(myLinkedList.length - 1));
//		System.out.println(myLinkedList.get(22));
		
//		MyLinkedList myLinkedList = new MyLinkedList();
//		myLinkedList.addAtHead(1);
//		myLinkedList.addAtTail(3);
//		myLinkedList.addAtIndex(1, 2);    			// linked list becomes 1->2->3
//        System.out.println(myLinkedList.get(1));    // return 2
//		myLinkedList.deleteAtIndex(1);    			// now the linked list is 1->3
//		System.out.println(myLinkedList.get(1));    // return 3
//		int idx = 0;
//		while(myLinkedList.get(idx) != -1) {
//			System.out.println(myLinkedList.get(idx++));
//		}
		
		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.addAtHead(1);
		myLinkedList.addAtHead(2);
		myLinkedList.addAtHead(7);
		myLinkedList.addAtIndex(3, 0);
		myLinkedList.deleteAtIndex(2);
		myLinkedList.addAtHead(6);
		myLinkedList.addAtTail(4);
		System.out.println(myLinkedList.get(4));
		myLinkedList.addAtHead(4);
		myLinkedList.addAtIndex(5, 0);
		myLinkedList.addAtHead(6);
//		int idx = 0;
//		while(myLinkedList.get(idx) != -1) {
//			System.out.println(myLinkedList.get(idx++));
//		}
	}
	
	private int length;
	private Node head;
	
	public MyLinkedList() {
		head = null;
		length = 0;
	}

	public int get(int index) {
		if(index >= length || index < 0) {
			return -1;
		} else {
			int currentIndex = 0;
			Node currentNode = head;
			if(currentIndex == index) {
				return currentNode.val;
			} else {
				while(currentIndex < index) {
					currentNode = currentNode.next;
					currentIndex++;
				}
				return currentNode.val;
			}
		}
	}

	public void addAtHead(int val) {
		Node node = new Node(val);
		node.next = head;
		head = node;
		length++;
	}

	public void addAtTail(int val) {
		if(length > 0) {
			Node currentNode = head;
			while(currentNode.next != null) {
				currentNode = currentNode.next;
			}
			Node newNode = new Node(val);
			currentNode.next = newNode;
			length++;
		} else {
			addAtHead(val);
		}
	}

	public void addAtIndex(int index, int val) {
		if(index < length) {
			if(index == 0) {
				addAtHead(val);
			} else {
				int currentIndex = 1;
				Node currentNode = head;
				while(currentIndex < index) {
					currentNode = currentNode.next;
					currentIndex++;
				}
				Node newNode = new Node(val);
				newNode.next = currentNode.next;
				currentNode.next = newNode;
				length++;
			}
		} else if(index == length) {
			addAtTail(val);
		}
	}

	public void deleteAtIndex(int index) {
		if(index < length && index >= 0) {
			if(index == 0) {
				head = head.next;
			} else {
				int currentIndex = 1;
				Node currentNode = head;
				while(currentIndex < index) {
					currentNode = currentNode.next;
					currentIndex++;
				}
				currentNode.next = currentNode.next.next;
			} 
			length--;
		}
	}
	
	private class Node {
		
		private int val;
		private Node next;
		
		@SuppressWarnings("unused")
		Node() {
			val = -1;
			next = null;
		}
		
		Node(int val) {
			this.val = val;
		}
		
		@SuppressWarnings("unused")
		Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return val + "";
		}
	}
}
