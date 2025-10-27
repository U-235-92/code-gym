package aq.gym.contests.linked_list;

import java.util.ArrayDeque;

public class ReorderList {

//	https://leetcode.com/problems/reorder-list/
	public static void main(String[] args) {
		ListNode one = new ListNode(1);
		ListNode two = new ListNode(2);
		ListNode three = new ListNode(3);
		ListNode four = new ListNode(4);
		ListNode five = new ListNode(5);
		one.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		new ReorderList().reorderList(one);
		ListNode node = one;
		while(node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}

    public void reorderList(ListNode head) { 
    	if(head.next == null) {
    		return;
    	} else if(head.next != null && head.next.next == null) {
    		return;
    	} else if(head.next != null && head.next.next != null && head.next.next.next == null) {
    		ListNode lastNode = head.next.next;
    		ListNode middleNode = head.next;
    		middleNode.next = null;
    		head.next = lastNode;
    		lastNode.next = middleNode;
    	} else {    		
    		ListNode middleNode = getMiddleNodeOfListNode(head);
    		ArrayDeque<ListNode> stack = getRightHalfListNodeStack(middleNode);
    		detechRightHalfOfListNode(middleNode);
    		mergeNodes(head, stack);
    	}
    }
    
    private ListNode getMiddleNodeOfListNode(ListNode headNode) {
    	ListNode slowNode = headNode;
    	ListNode fastNode = headNode.next.next;
    	while(fastNode != null && fastNode.next != null) {
    		slowNode = slowNode.next;
    		fastNode = fastNode.next.next;
    		if(fastNode != null && fastNode.next == null) slowNode = slowNode.next;
    	}
    	return slowNode;
    }
    
    private ArrayDeque<ListNode> getRightHalfListNodeStack(ListNode middleNode) {
    	ArrayDeque<ListNode> stack = new ArrayDeque<>();
    	ListNode node = middleNode.next;
    	while(node != null) {
    		stack.push(node);
    		node = node.next;
    	}
    	return stack;
    }
    
    private void detechRightHalfOfListNode(ListNode middleNode) {
    	middleNode.next = null;
    }
    
    private void mergeNodes(ListNode headNode, ArrayDeque<ListNode> stack) {
    	ListNode currNode = headNode;
    	while(!stack.isEmpty()) {
    		ListNode stackNode = stack.pop();
    		ListNode nextNode = currNode.next;
    		currNode.next = stackNode;
    		stackNode.next = nextNode;
    		currNode = nextNode;
    	}
    }
}
