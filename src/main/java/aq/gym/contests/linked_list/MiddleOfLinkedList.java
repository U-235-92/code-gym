package aq.gym.contests.linked_list;

public class MiddleOfLinkedList {

	public static void main(String[] args) {
		MiddleOfLinkedList moll = new MiddleOfLinkedList();
		ListNode head = moll.new ListNode(1, moll.new ListNode(2,
				moll.new ListNode(3, moll.new ListNode(4, moll.new ListNode(5)))));
		ListNode middle = moll.middleNode(head);
		while (middle != null) {
			System.out.print(middle.val + " ");
			middle = middle.next;
		}
	}

	public ListNode middleNode(ListNode head) {
		ListNode limit = head;
		ListNode middle = head;
		while (limit != null) {
			if (limit.next == null) {
				break;
			} else if (limit.next.next == null) {
				middle = middle.next;
				break;
			} else {
				middle = middle.next;
				limit = limit.next.next;
			}
		}
		return middle;
	}

//	 Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return "ListNode [val=" + val + "]";
		}

	}
}
