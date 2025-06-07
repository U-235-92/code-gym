package aq.gym.contests.hash_table;

import java.util.LinkedList;

public class MyHashSet {

	private static final int CAPACITY = 100;
	private LinkedList<Integer>[] buckets; 
	
	@SuppressWarnings("unchecked")
	public MyHashSet() {
		buckets = new LinkedList[CAPACITY];
	}
    
    public void add(int key) {
    	int pos = getPosition(key);
    	LinkedList<Integer> data = buckets[pos];
    	if(data == null) {
    		data = new LinkedList<>();
    		data.add(key);
    		buckets[pos] = data;
    	} else {
    		if(!data.contains(key)) {
    			data.add(key);
    		}
    	}
    }
    
    public void remove(int key) {
    	int pos = getPosition(key);
    	LinkedList<Integer> data = buckets[pos];
    	if(data != null) {
    		data.removeFirstOccurrence(key);
    	}
    }
    
    public boolean contains(int key) {
    	int pos = getPosition(key);
    	LinkedList<Integer> data = buckets[pos];
    	return data != null && data.contains(key);
    }
	
    private int getPosition(int value) {
    	return value % CAPACITY;
    }
    
	public static void main(String[] args) {
		MyHashSet myHashSet = new MyHashSet();
		myHashSet.add(1);
		myHashSet.add(2);
		System.out.println(myHashSet.contains(1));
		System.out.println(myHashSet.contains(3));
		myHashSet.add(2);
		System.out.println(myHashSet.contains(2));
		myHashSet.remove(2);
		System.out.println(myHashSet.contains(2));
		myHashSet.add(2);
		myHashSet.add(2);
		System.out.println(myHashSet.contains(2));
	}
}
