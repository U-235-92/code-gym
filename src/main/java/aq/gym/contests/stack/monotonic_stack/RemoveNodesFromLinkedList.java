package aq.gym.contests.stack.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveNodesFromLinkedList {

//	https://leetcode.com/problems/remove-nodes-from-linked-list/
	public static void main(String[] args) {
//		ListNode head = new ListNode(5, new ListNode(2, new ListNode(13, new ListNode(3, new ListNode(8)))));
//		ListNode head = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1))));
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
		ListNode head = new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1))));
		ListNode result = new RemoveNodesFromLinkedList().removeNodes(head);
		while(result != null) {
			System.out.print(result.val + " ");
			result = result.next;
		}
	}

    public ListNode removeNodes(ListNode head) {
    	if(head.next == null) {
    		return head;
    	}
        Deque<ListNode> deque = new ArrayDeque<>();
        while(head != null) {
        	if(deque.isEmpty()) {
        		deque.push(head);
        		head = head.next;
        	} else {
        		if(deque.peek().val < head.val) {
        			while(!deque.isEmpty() && deque.peek().val < head.val) {
        				deque.pop();
        			}
        			deque.push(new ListNode(head.val));
        			head = head.next;
        		} else {
        			deque.push(new ListNode(head.val));
        			head = head.next;
        		}
        	}
        }
        return getUpdatatedLinkedList(deque);
    }
    
    private ListNode getUpdatatedLinkedList(Deque<ListNode> deque) {
    	ListNode head = null, curr = null, next = null;
        while(!deque.isEmpty()) {
        	if(head == null) {        		
        		head = deque.pollLast();
        		curr = head;
        	} else {
        		next = deque.pollLast();
        		curr.next = next;
        		curr = curr.next;
        	}
        }
        return head;
    }
    
    private static class ListNode {

   	 protected int val;
   	 protected ListNode next;
   	 protected ListNode(int val) { this.val = val; }
   	 protected ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   	 @Override
   	 public String toString() { return val + ""; }
   }
}
