package aq.gym.contests.linked_list;

public class ListNode {

	 protected int val;
	 protected ListNode next;
	 protected ListNode() {}
	 protected ListNode(int val) { this.val = val; }
	 protected ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 @Override
	 public String toString() { return val + ""; }
}
