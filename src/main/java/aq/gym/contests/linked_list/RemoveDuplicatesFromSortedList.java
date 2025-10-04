package aq.gym.contests.linked_list;

public class RemoveDuplicatesFromSortedList {

	public static void main(String[] args) {
		RemoveDuplicatesFromSortedList rdfsl = new RemoveDuplicatesFromSortedList();
//		ListNode duplicateList = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, null)))));
//		ListNode duplicateList = new ListNode(1, new ListNode(1, null));
//		ListNode duplicateList = new ListNode(-100, new ListNode(-100, new ListNode(4, null)));
		ListNode duplicateList = new ListNode();
		ListNode distinctList = rdfsl.deleteDuplicates(duplicateList);
		while(distinctList != null) {
			System.out.print(distinctList.val + " ");
			distinctList = distinctList.next;
		}
	}

    public ListNode deleteDuplicates(ListNode head) {
    	ListNode list = null;
        ListNode curr = null;
        int currVal = 0, prevVal = 0;
        while(head != null) {
        	currVal = head.val;
        	if(list == null) {
        		curr = new ListNode(currVal);
        		list = curr;
        	} else {
        		if(currVal != prevVal) {
        			ListNode next = new ListNode(currVal);
        			curr.next = next;
        			curr = next;
        		} 
        	}
        	prevVal = currVal;
        	head = head.next;
        }
        return list;
    }
	
    @SuppressWarnings("unused")
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
