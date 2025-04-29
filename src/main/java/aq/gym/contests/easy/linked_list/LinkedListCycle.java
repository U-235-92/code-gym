package aq.gym.contests.easy.linked_list;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

	public static void main(String[] args) {
		LinkedListCycle llc = new LinkedListCycle();
		ListNode ln1 = llc.new ListNode(1);
		ListNode ln2 = llc.new ListNode(2);
		ListNode ln3 = llc.new ListNode(3);
		ListNode ln4 = llc.new ListNode(4);
		ln1.next = ln2;
		ln2.next = ln3;
		ln3.next = ln4;
		ln4.next = ln1;
		System.out.println(llc.hasCycle(ln1));
		System.out.println(llc.detectCycle(ln1));
	}

	public ListNode detectCycle(ListNode head) {
		if(head == null) {
			return null;
		}
		if(head.next == null) {
			return null;
		} else {
			Set<ListNode> visitedNodes = new HashSet<>();
			ListNode current = head;
			visitedNodes.add(current);
			while(current.next != null) {
				current = current.next;
				if(visitedNodes.contains(current)) {
					return current;
				} else {
					visitedNodes.add(current);
				}
			}
			return null;
		}
    }
	
	public boolean hasCycle(ListNode head) {
		if(head == null) {
			return false;
		}
		if(head.next == null) {
			return false;
		} else {
			ListNode slow = head;
			ListNode fast = slow.next.next;
			if(fast == null) {
				return false;
			}
			if(fast == slow) {
				return true;
			} else {
				while(true) {
					slow = slow.next;
					if(fast.next == null) {
						return false;
					} else {						
						fast = fast.next.next;
						if(fast == slow) {
							return true;
						} else if(fast == null) {
							return false;
						}
					}
				}
			}
		}
	}
	
	private class ListNode {
		private int val;
		private ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
		
		@Override
		public String toString() {
			return "" + val;
		}
	}
}
