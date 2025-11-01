package aq.gym.contests.linked_list;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NodesFromLinkedListPresentInArray {

//	https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/description
	public static void main(String[] args) {
//		int[] nums = {1,2,3};
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//		int[] nums = {1};
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(1, new ListNode(2, new ListNode(1, new ListNode(2))))));
		int[] nums = {5};
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
		ListNode result = new NodesFromLinkedListPresentInArray().modifiedList(nums, head);
		while(result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}

    public ListNode modifiedList(int[] nums, ListNode head) {
    	Set<Integer> numsSet = Arrays.stream(nums).mapToObj(Integer::valueOf).collect(Collectors.toSet());
    	Map<Integer, Integer> nodesMap = getNodesMap(head);
    	substractValues(nodesMap, numsSet);
    	ListNode result = getModifiedList(nodesMap);
    	return result;
    }
    
    private Map<Integer, Integer> getNodesMap(ListNode head) {
    	Map<Integer, Integer> nodesMap = new LinkedHashMap<Integer, Integer>();
    	int nodeIdx = 0;
    	while(head != null) {
    		nodesMap.put(nodeIdx++, head.val);
    		head = head.next;
    	}
    	return nodesMap;
    }
    
    private void substractValues(Map<Integer, Integer> minuend, Set<Integer> subtrahend) {
    	minuend.values().removeAll(subtrahend);
    }
    
    private ListNode getModifiedList(Map<Integer, Integer> nodesMap) {
    	ListNode result = null;
    	ListNode curr = null;
    	for(Integer val : nodesMap.values()) {
    		if(result == null) {
    			result = new ListNode(val);
    			curr = result;
    		} else {
    			ListNode next = new ListNode(val);
    			curr.next = next;
    			curr = next;
    		}
    	}
    	return result;
    }
}
