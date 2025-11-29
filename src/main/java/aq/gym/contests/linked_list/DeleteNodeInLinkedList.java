package aq.gym.contests.linked_list;

public class DeleteNodeInLinkedList {

//	https://leetcode.com/problems/delete-node-in-a-linked-list/
	public static void main(String[] args) {
		ListNode head = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n1 = new ListNode(1);
		ListNode n9 = new ListNode(9);
		head.next = n5;
		n5.next = n1;
		n1.next = n9; 
		new DeleteNodeInLinkedList().deleteNode(n1);
		while(head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

    public void deleteNode(ListNode node) {
    	ListNode prev = null;
        ListNode curr = node;
        ListNode next = curr.next;
        while(curr.next != null) {
        	int tmp = curr.val;
        	curr.val = next.val;
        	next.val = tmp;
        	prev = curr;
        	curr = next;
        	next = next.next;
        }
        prev.next = null;
    }
}
