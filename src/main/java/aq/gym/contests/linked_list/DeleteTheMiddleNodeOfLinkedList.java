package aq.gym.contests.linked_list;

public class DeleteTheMiddleNodeOfLinkedList {

//	https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
	public static void main(String[] args) {
//		ListNode head = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(7, new ListNode(1, new ListNode(2, new ListNode(6)))))));
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		ListNode head = new ListNode(1, new ListNode(2));
		ListNode head = new ListNode(1);
		ListNode aswr = new DeleteTheMiddleNodeOfLinkedList().deleteMiddle(head);
		while(aswr != null) {
			System.out.print(aswr.val + " ");
			aswr = aswr.next;
		}
	}

    public ListNode deleteMiddle(ListNode head) {
    	if(head.next == null) {
    		return null;
    	}
    	if(head.next.next == null) {
    		head.next = null;
    		return head;
    	}
    	if(head.next.next.next == null) {
    		head.next = head.next.next;
    		return head;
    	}
        ListNode fast = head, slow = head, middle = null, prev = null, next = null;
        while(true) {
        	slow = slow.next;
        	fast = fast.next.next;
        	if(fast == null || fast.next == null) {
        		middle = slow;
        		next = middle.next;
        		break;
        	}
        	prev = slow;
        }
        prev.next = next;
        return head;
    }
}
