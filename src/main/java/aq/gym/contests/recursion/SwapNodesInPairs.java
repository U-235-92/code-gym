package aq.gym.contests.recursion;

public class SwapNodesInPairs {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode next = new ListNode(2);
		ListNode txen = new ListNode(3);
		ListNode xent = new ListNode(4);
		ListNode last = new ListNode(5);
		head.next = next;
		next.next = txen;
		txen.next = xent;
		xent.next = last;
		ListNode result = new SwapNodesInPairs().swapPairs(head);
		while(result != null) {
			System.out.print(result + " ");
			result = result.next;
		}
	}

	public ListNode swapPairs(ListNode head) {
		if(head == null)
			return head;
		if(head.next == null)
			return head;
		head = swapPairs(head, head.next);
		return head;
    }
	
	private ListNode swapPairs(ListNode head, ListNode prev) {
		if(head == null || head.next == null)
			return prev;
		ListNode tmp = head;
		ListNode tmpNext = head.next.next;
		head = head.next;
		head.next = tmp;
		head.next.next = tmpNext;
		swapPairs(head.next.next, head.next);
		if(head != prev)
			prev.next = head;
		return head;
	}
	
	@SuppressWarnings("unused")
	private static class ListNode {
		
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
			return val + "";
		}
	}
}
