package aq.gym.contests.easy.linked_list;

import java.util.HashSet;
import java.util.Set;

public class IntersectionTwoLinkedLists {

	public static void main(String[] args) {
		IntersectionTwoLinkedLists itll = new IntersectionTwoLinkedLists();
		ListNode a1 = itll.new ListNode(11);
		ListNode a2 = itll.new ListNode(12);
		ListNode a3 = itll.new ListNode(13);
		ListNode a4 = itll.new ListNode(14);
		ListNode a5 = itll.new ListNode(15);
		ListNode b1 = itll.new ListNode(21);
		ListNode b2 = itll.new ListNode(22);
		ListNode b3 = itll.new ListNode(23);
		ListNode c1 = itll.new ListNode(31);
		ListNode c2 = itll.new ListNode(32);
		ListNode c3 = itll.new ListNode(33);
//		null
//		a1.next = a2;
//		a2.next = a3;
//		b1.next = b2;
		
//		31
//		a1.next = c1;
//		b1.next = c1;
		
//		21
//		a1.next = a2;
//		a2.next = a3;
//		a3.next = b1;
//		b1.next = b2;
		
//		31
//		a1.next = a2; 
//		a2.next = a3;
//		a3.next = a4;
//		a4.next = a5;
//		a5.next = c1;
//		b1.next = c1;
//		c1.next = c2;
//		c2.next = c3;
		
//		31
//		a1.next = a2; 
//		a2.next = a3;
//		a3.next = c1;
//		b1.next = b2;
//		b2.next = c1;
//		c1.next = c2;
//		c2.next = c3;
		ListNode intersection = itll.getIntersectionNodeByCrossPointers(a1, b1);
		System.out.println(intersection);
	}

	public ListNode getIntersectionNodeByCrossPointers(ListNode headA, ListNode headB) {
		if(headA == null || headB == null) {
			return null;
		}
		if(headA == headB) {
			return headA;
		}
		ListNode currentA = headA;
		ListNode currentB = headB;
		while(currentA != currentB) {
			if(currentA == null) {
				currentA = headB;
			} else {
				currentA = currentA.next;
			}
			if(currentB == null) {
				currentB = headA;
			} else {
				currentB = currentB.next;
			}
		}
		return currentA; //Valid answer both currentA and currentB 
	}
	
	public ListNode getIntersectionNodeBySet(ListNode headA, ListNode headB) {
		if(headA == null || headB == null) {
			return null;
		}
		if(headA == headB) {
			return headA;
		}
		ListNode currentA = headA;
		ListNode currentB = headB;
		Set<ListNode> visited = new HashSet<>();
		visited.add(currentA);
		visited.add(currentB);
        while(currentA != null && currentB != null) {
        	currentA = currentA.next;
        	currentB = currentB.next;
        	if(currentA == currentB) {
        		return currentA;
        	}
        	if(visited.contains(currentA)) {
        		return currentA;
        	}
        	if(visited.contains(currentB)) {
        		return currentB;
        	}
        	visited.add(currentA);
        	visited.add(currentB);
        }
        while(currentA != null) {
        	currentA = currentA.next;
        	if(visited.contains(currentA)) {
        		return currentA;
        	} else {
        		visited.add(currentA);
        	}
        }
        while(currentB != null) {
        	currentB = currentB.next;
        	if(visited.contains(currentB)) {
        		return currentB;
        	} else {
        		visited.add(currentB);
        	}
        }
        return null;
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
