package aq.gym.contests.easy.linked_list;

public class MyLinkedList {
	
	public static void main(String[] args) {
		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.addAtHead(2);
		myLinkedList.addAtHead(5);
		myLinkedList.addAtHead(8);
		System.out.println(myLinkedList.get(0));
		System.out.println(myLinkedList.get(1));
		System.out.println(myLinkedList.get(3));
		System.out.println();
	}
	
	private int length;
	private Node head;
	
	public MyLinkedList() {
		head = null;
		length = 0;
	}

	public int get(int index) {
		if(index >= length) {
			return -1;
		} else {
			int currentIndex = 0;
			Node currentNode = head;
			if(currentIndex == index) {
				return currentNode.val;
			} else {
				while(currentIndex != index) {
					currentNode = currentNode.next;
					currentIndex++;
					if(currentIndex == index) {
						return currentNode.val;
					}
				}
			}
		}
		return -1;
	}

	public void addAtHead(int val) {
		Node node = new Node(val);
		node.next = head;
		head = node;
		length++;
	}

	public void addAtTail(int val) {
		
	}

	public void addAtIndex(int index, int val) {
		
	}

	public void deleteAtIndex(int index) {
		
	}
	
	private class Node {
		
		private int val;
		private Node next;
		
		Node() {
			val = -1;
			next = null;
		}
		
		Node(int val) {
			this.val = val;
		}
		
		Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
	}
}
