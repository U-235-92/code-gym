package aq.gym.contests.linked_list;

public class MergeTwoSortedLists {

	public static void main(String[] args) {
		MergeTwoSortedLists mtsl = new MergeTwoSortedLists();
		ListNode l1_n1 = mtsl.new ListNode(1);
		ListNode l1_n2 = mtsl.new ListNode(2);
		ListNode l1_n3 = mtsl.new ListNode(3);
		ListNode l2_n1 = mtsl.new ListNode(4);
		ListNode l2_n2 = mtsl.new ListNode(5);
		ListNode l2_n3 = mtsl.new ListNode(6);
		l1_n1.next = l1_n2; l1_n2.next = l1_n3;
		l2_n1.next = l2_n2; l2_n2.next = l2_n3;
		ListNode result = mtsl.mergeTwoLists(l1_n1, l2_n1);
		while(result != null) {
			System.out.print(result + " ");
			result = result.next;
		}
	}

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null)
        	return null;
        if(list1 == null && list2 != null) 
        	return list2;
        if(list1 != null && list2 == null)
        	return list1;
        ListNode head = new ListNode();
        ListNode curr = head;
        while(list1 != null && list2 != null) {
        	if(list1.val <= list2.val) {
        		curr.next = list1;
        		list1 = list1.next;
        	} else {
        		curr.next = list2;
        		list2 = list2.next;
        	}
        	curr = curr.next;
        }
        while(list1 != null) {
        	curr.next = list1;
        	list1 = list1.next;
        	curr = curr.next;
        }
        while(list2 != null) {
        	curr.next = list2;
        	list2 = list2.next;
        	curr = curr.next;
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
