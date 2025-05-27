package aq.gym.contests.recursion;

public class ReverseLinkedList {

	public static void main(String[] args) {
		ReverseLinkedList rll = new ReverseLinkedList();
		ListNode n1 = rll.new ListNode(1);
		ListNode n2 = rll.new ListNode(2);
		ListNode n3 = rll.new ListNode(3);
		ListNode n4 = rll.new ListNode(4);
		ListNode n5 = rll.new ListNode(5);
		n1.next = n2; 
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		ListNode result = rll.reverseList(n1);
		while(result != null) {
			System.out.print(result + " ");
			result = result.next;
		}
	}

	public ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		if (head.next != null && head.next.next == null) {
			ListNode first = head;
			ListNode second = head.next;
			second.next = first;
			first.next = null;
			return second;
		}
		return reverse(null, head, head.next);
	}
	
	private ListNode reverse(ListNode prev, ListNode curr, ListNode next) {
		if(next == null)
			return curr;
		ListNode tmp = next.next;
		next.next = curr;
		curr.next = prev;
		prev = curr;
		ListNode node = reverse(prev, next, tmp);
		return node;
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
