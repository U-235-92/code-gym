package aq.gym.contests.hash_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyHashMap {

	private static final int CAPACITY = 100;
	private List<Bucket>[] map;
	
	@SuppressWarnings("unchecked")
	public MyHashMap() {
        map = new ArrayList[CAPACITY];
    }
    
    public void put(int key, int value) {
    	int pos = getPosition(key);
    	List<Bucket> data = map[pos];
    	if(data == null) {
    		data = new ArrayList<>();
    		data.add(new Bucket(key, value));
    		map[pos] = data;
    	} else {
    		Bucket bucket = new Bucket(key, value);
    		int idx = data.indexOf(bucket);
    		if(idx == -1) {
    			data.add(bucket);
    		} else {
    			data.set(idx, bucket);
    		}
    	}
    }
    
    public int get(int key) {
    	int pos = getPosition(key);
        List<Bucket> data = map[pos];
        if(data == null) {
        	return -1;
        } else {
        	for(Bucket bucket : data) {
        		if(bucket.key == key) {
        			return bucket.value;
        		}
        	}
        	return -1;
        }
    }
    
    public void remove(int key) {
    	int pos = getPosition(key);
        List<Bucket> data = map[pos];
        if(data != null) {
        	for(int i = 0; i < data.size(); i++) {
        		if(data.get(i).key == key) {
        			data.remove(i);
        			return;
        		}
        	}
        } 
    }
	
    private int getPosition(int key) {
    	return key % CAPACITY;
    }
    
    private static class Bucket {
    	
    	private int key;
    	private int value;
    	
    	public Bucket(int key, int value) {
			this.key = key;
			this.value = value;
		}
    	
    	@Override
    	public int hashCode() {
    		return Objects.hash(key);
    	}
    	
    	@Override
    	public boolean equals(Object obj) {
    		if(obj == null || obj.getClass() != getClass()) {
    			return false;
    		}
    		if(obj == this) {
    			return true;
    		}
    		Bucket bucket = (Bucket) obj;
    		return bucket.key == key;
    	}
    	
    	@Override
    	public String toString() {
    		return key + " : " + value;
    	}
    }
    
	public static void main(String[] args) {
		MyHashMap map = new MyHashMap();
		map.put(1, 200);
		map.put(2, 300);
		map.put(2, 400);
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		map.remove(2);
		System.out.println(map.get(2));
	}
}
