package aq.gym.contests.linked_list;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumTwinSumOfLinkedList {

//	https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
	public static void main(String[] args) {
//		ListNode head = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));
//		ListNode head = new ListNode(4, new ListNode(2, new ListNode(2, new ListNode(3))));
		ListNode head = new ListNode(1, new ListNode(100000));
		System.out.println(new MaximumTwinSumOfLinkedList().pairSum(head));
	}

    public int pairSum(ListNode head) {
    	int maxTwinSum = 0;
    	ListNode curr = head;
    	Deque<ListNode> stack = getRightHalfNodeStack(head);
    	while(!stack.isEmpty()) {
    		ListNode twin = stack.pop();
    		maxTwinSum = Math.max(maxTwinSum, twin.val + curr.val);
    		curr = curr.next;
    	}
    	return maxTwinSum;
    }
    
    private Deque<ListNode> getRightHalfNodeStack(ListNode head) {
    	Deque<ListNode> stack = new ArrayDeque<ListNode>();
    	ListNode slow = head, fast = head;
    	while(fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	while(slow != null) {
    		stack.push(slow);
    		slow = slow.next;
    	}
    	return stack;
    }
}
