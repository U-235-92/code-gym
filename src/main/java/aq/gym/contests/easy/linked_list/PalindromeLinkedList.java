package aq.gym.contests.easy.linked_list;

public class PalindromeLinkedList {

	public static void main(String[] args) {
		PalindromeLinkedList pll = new PalindromeLinkedList();
		ListNode n1 = pll.new ListNode(1);
		ListNode n2 = pll.new ListNode(0);
		ListNode n3 = pll.new ListNode(4);
		ListNode n4 = pll.new ListNode(4);
		ListNode n5 = pll.new ListNode(0);
		ListNode n6 = pll.new ListNode(1);
		n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5; n5.next = n6;
		boolean isPalindrome = pll.isPalindrome(n1);
		System.out.println(isPalindrome);
	}

	public boolean isPalindrome(ListNode head) {
		if(head.next == null) {
			return true;
		}
		if(head.next != null && head.val == head.next.val && head.next.next == null) {
			return true;
		}
		if(head.next != null && head.val != head.next.val && head.next.next == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		} 
		if(fast != null) {
			slow = slow.next;
		}
		ListNode reverse = reverse(slow);
		ListNode curr = head;
		while(reverse != null) {
			int valLeft = curr.val;
			int valRight = reverse.val;
			if(valLeft != valRight) {
				return false;
			}
			curr = curr.next;
			reverse = reverse.next;
		}
		return true;
	}
	
	private ListNode reverse(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		ListNode next = head.next;
		while(curr != null && next != null) {
			curr.next = prev;
			prev = curr;
			curr = next;
			next = next.next;
		}
		curr.next = prev;
        return curr;
	}
	
	public class ListNode {
		private int val;
		private ListNode next;

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
			return "" + val;
		}
	}
}
