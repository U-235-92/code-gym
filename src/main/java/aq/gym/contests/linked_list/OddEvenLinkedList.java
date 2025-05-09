package aq.gym.contests.linked_list;

public class OddEvenLinkedList {

	public static void main(String[] args) {
		OddEvenLinkedList oell = new OddEvenLinkedList();
		ListNode n1 = oell.new ListNode(1);
		ListNode n2 = oell.new ListNode(2);
		ListNode n3 = oell.new ListNode(3);
		ListNode n4 = oell.new ListNode(4);
		ListNode n5 = oell.new ListNode(5);
		ListNode n6 = oell.new ListNode(6);
		ListNode n7 = oell.new ListNode(7);
		n2.next = n1; n1.next = n3; n3.next = n5; n5.next = n6; n6.next = n4; n4.next = n7;
		ListNode result = oell.oddEvenList(n2);
//		n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
//		ListNode result = oell.oddEvenList(n1);
		while(result != null) {
			System.out.print(result + " ");
			result = result.next;
		}
	}


	public ListNode oddEvenList(ListNode head) {
		if(head == null) {
			return null;
		}
		if(head.next == null) {
			return head;
		}
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenStart = head.next;
        while(odd.next != null && even.next != null) {
        	odd.next = even.next;
        	odd = odd.next;
        	even.next = odd.next;
        	even = odd.next;
        }
        odd.next = evenStart;
        return head;
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
