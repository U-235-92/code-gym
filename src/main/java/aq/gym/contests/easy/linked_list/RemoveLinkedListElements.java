package aq.gym.contests.easy.linked_list;

public class RemoveLinkedListElements {

	public static void main(String[] args) {
		RemoveLinkedListElements rlle = new RemoveLinkedListElements();
		ListNode n1 = rlle.new ListNode(1);
		ListNode n2 = rlle.new ListNode(2);
		ListNode n6_1 = rlle.new ListNode(6);
		ListNode n3 = rlle.new ListNode(3);
		ListNode n4 = rlle.new ListNode(4);
		ListNode n5 = rlle.new ListNode(5);
		ListNode n6_2 = rlle.new ListNode(6);
		n1.next = n2; n2.next = n6_1; n6_1.next = n3; n3.next = n4; n4.next = n5; n5.next = n6_2;
		ListNode result1 = rlle.removeElements(n1, 6);
		rlle.printResult(result1);
		ListNode n10 = rlle.new ListNode(10);
		ListNode result2 = rlle.removeElements(n10, 2);
		rlle.printResult(result2);
		ListNode n8_1 = rlle.new ListNode(8);
		ListNode n8_2 = rlle.new ListNode(8);
		ListNode n8_3 = rlle.new ListNode(8);
		n8_1.next = n8_2; n8_2.next = n8_3;
		ListNode result3 = rlle.removeElements(n8_1, 8);
		rlle.printResult(result3);
		ListNode result4 = rlle.removeElements(null, 5);
		rlle.printResult(result4);
		ListNode n1_1 = rlle.new ListNode(1);
		ListNode n1_2 = rlle.new ListNode(2);
		ListNode n1_3 = rlle.new ListNode(3);
		ListNode n5_1 = rlle.new ListNode(5);
		ListNode n5_2 = rlle.new ListNode(5);
		n1_1.next = n1_2; n1_2.next = n1_3; n1_3.next = n5_1; n5_1.next = n5_2;
		ListNode result5 = rlle.removeElements(n1_1, 5);
		rlle.printResult(result5);
	}

	public ListNode removeElements(ListNode head, int val) {
        if(head == null) {
        	return null;
        }
        if(head.val != val && head.next == null) {
        	return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;
        while(curr != null) {
        	if(curr.val == val) {
        		if(prev == null) {
        			if(curr == head) {
        				head = next;
        			}
        			curr.next = null;
        			curr = next;
        		} else {        			
        			prev.next = next;
        			curr.next = null;
        			curr = next;
        		}
        	} else {
        		prev = curr;
        		curr = next;
        	}
        	if(next != null) {
        		next = next.next;
    		} else {
    			break;
    		}
        }
        return head;
    }

	private void printResult(ListNode head) {
		if(head == null) {
			System.out.println(null + "");
			return;
		}
		while(head != null) {
			System.out.print(head + " ");
			head = head.next;
		}
		System.out.println();
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
