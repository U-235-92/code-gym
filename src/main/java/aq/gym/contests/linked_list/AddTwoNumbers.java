package aq.gym.contests.linked_list;

public class AddTwoNumbers {

	public static void main(String[] args) {
		AddTwoNumbers atn = new AddTwoNumbers();
		ListNode n1_n1 = atn.new ListNode(9);
		ListNode n1_n2 = atn.new ListNode(9);
		ListNode n1_n3 = atn.new ListNode(9);
		ListNode n1_n4 = atn.new ListNode(9);
		ListNode n1_n5 = atn.new ListNode(9);
		ListNode n1_n6 = atn.new ListNode(9);
		ListNode n1_n7 = atn.new ListNode(9);
		ListNode n2_n1 = atn.new ListNode(9);
		ListNode n2_n2 = atn.new ListNode(9);
		ListNode n2_n3 = atn.new ListNode(9);
		ListNode n2_n4 = atn.new ListNode(9);
		n1_n1.next = n1_n2; n1_n2.next = n1_n3; n1_n3.next = n1_n4; n1_n4.next = n1_n5; n1_n5.next = n1_n6; n1_n6.next = n1_n7;
		n2_n1.next = n2_n2; n2_n2.next = n2_n3; n2_n3.next = n2_n4;
		ListNode result = atn.addTwoNumbers(n1_n1, n2_n1);
		while(result != null) {
			System.out.print(result + " ");
			result = result.next;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode curr = head;
		int carry = 0;
		while(l1 != null || l2 != null) {
			int sum = 0;
			if(l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if(l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			sum += carry;
			carry = sum / 10;
			sum = sum % 10;
			ListNode subresult = new ListNode(sum);
			curr.next = subresult;
			curr = curr.next;
		}
		if(carry > 0) {
			ListNode subresult = new ListNode(carry);
			curr.next = subresult;
		}
		return head.next;
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
