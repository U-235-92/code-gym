package aq.gym.contests.easy.linked_list;

public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {
		RemoveNthNodeFromEndOfList rnnfeol = new RemoveNthNodeFromEndOfList();
		ListNode n1 = rnnfeol.new ListNode(1);
		ListNode n2 = rnnfeol.new ListNode(2);
		ListNode n3 = rnnfeol.new ListNode(3);
		ListNode n4 = rnnfeol.new ListNode(4);
		ListNode n5 = rnnfeol.new ListNode(5);
		n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
		ListNode result = rnnfeol.removeNthFromEnd(n1, 5);
		System.out.print(result + " ");
		while(result.next != null) {
			result = result.next;
			System.out.print(result + " ");
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(n == 1) {
			if(head.next == null) {				
				head = null;
				return head;
			} else {
				ListNode slow = head;
				ListNode fast = head.next;
				while(fast.next != null) {
					slow = slow.next;
					fast = fast.next;
				}
				slow.next = null;
				return head;
			}
		} else {			
			ListNode slow = head;
			ListNode fast = head;
			for(int i = 0; i < n; i++) {
				fast = fast.next;
			}
			if(fast == null) { // It uses when n = LinkedList size
				head = head.next;
			} else {				
				while(fast.next != null) {
					slow = slow.next;
					fast = fast.next;
				}
				slow.next = slow.next.next;
			}
			return head;
		}
	}

	private class ListNode {
		private int val;
		private ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}

		@Override
		public String toString() {
			return "" + val;
		}
	}
}
