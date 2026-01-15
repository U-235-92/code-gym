package aq.gym.contests.realization;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// https://leetcode.com/problems/lru-cache/description/
// put(1,1) -> [1], lru = 1; put(2,2) -> [1,2] lru = 2; etc...
// 1 2 3 get(1) -> 2 3 1 <- lru = 1 
// 1 2 3 get(2) -> 1 3 2 <- lru = 2
// 1 2 3 get(3) -> 1 2 3 <- lru = 3
public class LRUCache {
	
	public static void main(String[] args) {
		ManualLRUCache cache = new ManualLRUCache(1);
		cache.put(1, 1);  
		cache.put(2, 2);  
		System.out.println(cache.get(1));
		cache.put(3, 3); 
		System.out.println(cache.get(2)); 
		cache.put(4, 4); 
		System.out.println(cache.get(1)); 
		System.out.println(cache.get(3)); 
		System.out.println(cache.get(4)); 
		cache.put(5, 5); 
		System.out.println(cache.get(3));
	}
}

class ManualLRUCache {
	
	private Node head;
	private Node tail;
	private final int capacity;
	private Map<Integer, Node> cache;
	
	ManualLRUCache(int capacity) {
		this.cache = new HashMap<>();
		this.capacity = capacity;
	}
	
    public int get(int key) {
    	if(cache.containsKey(key)) {
    		Node node = cache.get(key);
    		moveNode(node);
    		return node.value;
    	} 
    	return -1;
    }
    
    public void put(int key, int value) {
    	if(cache.containsKey(key)) {
    		Node node = cache.get(key);
    		node.value = value;
    		moveNode(node);
    	} else {
    		Node node = new Node(key, value);
    		if(head == null && tail == null) {
    			head = node;
    			tail = node;
    		} else {    			
    			moveNode(node);
    		}
    		cache.put(key, node);
    	}
    	if(cache.size() > capacity) {
    		cache.remove(head.key);
    		head = head.next;
            if(head != null) {
                head.prev = null;
            } else {
                tail = null; 
            }
    	}
    }
    
    private void moveNode(Node node) {
    	if(node == tail) {
    		return;
    	} else if(node == head) {
    		head = head.next;
    		if(head == null) {
    			tail = null;
    		} else {
    			head.prev = null;
    			updateTail(node);
    		}
    	} else {
    		if(node.prev != null && node.next != null) {    			
    			Node prev = node.prev;
    			Node next = node.next;
    			prev.next = next;
    			next.prev = prev;
    		} 
    		updateTail(node);
    	}
    }
    
    private void updateTail(Node node) {
    	tail.next = node;
		node.next = null;
		node.prev = tail;
		tail = node;
    }
	
	private class Node {
		
		private int key;
		private int value;
		private Node next;
		private Node prev;
		
		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}

class LinkedHashMapLRUCache extends LinkedHashMap<Integer, Integer> {
	
	private static final long serialVersionUID = 1L;
	
	private final int capacity;
	
	LinkedHashMapLRUCache(int capacity) {
		super(capacity, 0.75f, true);
		this.capacity = capacity;
    }
    
    public int get(int key) {
    	return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
    	super.put(key, value);
    }
    
    @Override
	protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
    	return super.size() > capacity;
	}
}