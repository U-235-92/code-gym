package aq.gym.contests.linked_list;

import java.util.Map;
import java.util.TreeMap;

public class RemoveDuplicatesFromSortedListII {

	public static void main(String[] args) {
		RemoveDuplicatesFromSortedListII rdfsl = new RemoveDuplicatesFromSortedListII();
//		ListNode duplicateList = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5, null)))))));
//		ListNode duplicateList = new ListNode(1, new ListNode(1, new ListNode(1)));
//		ListNode duplicateList = new ListNode(1);
//		ListNode duplicateList = new ListNode(1, new ListNode(1));
//		ListNode duplicateList = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, null)))));
//		ListNode duplicateList = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, null))));
//		ListNode duplicateList = new ListNode(1, new ListNode(3, new ListNode(3, null)));
//		ListNode duplicateList = null;
		ListNode duplicateList = new ListNode(-3, new ListNode(-1, new ListNode(0, new ListNode(0, new ListNode(0, new ListNode(3, new ListNode(3, null))))))); 
		ListNode distinctList = rdfsl.deleteDuplicates(duplicateList);
		while(distinctList != null) {
			System.out.print(distinctList.val + " ");
			distinctList = distinctList.next;
		}
	}

    public ListNode deleteDuplicates(ListNode head) { 
    	if(head == null) {
    		return null;
    	} else {
    		return delete(head);
    	}
    }
    
    @SuppressWarnings("unused")
	private ListNode delete(ListNode head) {
    	Map<Integer, Integer> frequencies = new TreeMap<>();
    	while(head != null) {
    		int val = head.val;
    		frequencies.compute(val, (k, v) -> {
    			if(v == null) {
    				return 1;
    			} else {
    				return v + 1;
    			}
    		});
    		head = head.next;
    	}
    	ListNode result = null, node = null;
    	for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
    		if(entry.getValue() == 1) {    			
    			if(result == null) {
    				node = new ListNode(entry.getKey());
    				result = node;
    			} else {
    				ListNode next = new ListNode(entry.getKey());
    				node.next = next;
    				node = next;
    			}
    		}
    	}
    	return result;
    }
	
	@SuppressWarnings("unused")
	private static class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
		
		@Override
		public String toString() {
			return "ListNode [val=" + val + ", next=" + next + "]";
		}
	}
}
