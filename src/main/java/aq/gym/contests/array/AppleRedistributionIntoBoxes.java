package aq.gym.contests.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppleRedistributionIntoBoxes {

//	https://leetcode.com/problems/apple-redistribution-into-boxes
	public static void main(String[] args) {
//		int[] apple = {1,3,2}, capacity = {4,3,1,5,2};
//		int[] apple = {5,5,5}, capacity = {2,4,2,7};
		int[] apple = {5}, capacity = {2,4,2,7};
		System.out.println(new AppleRedistributionIntoBoxes().minimumBoxes(apple, capacity));
	}

    public int minimumBoxes(int[] apple, int[] capacity) {
    	Arrays.sort(apple);
    	Arrays.sort(capacity);
    	Map<Integer, Integer> boxes = new HashMap<Integer, Integer>();
    	for(int i = apple.length - 1, j = capacity.length - 1; i >= 0 && j >= 0;) {
    		if(boxes.isEmpty() || boxes.get(j) == null) {
    			boxes.put(j, capacity[j]);
    		} 
    		int curCapacity = boxes.get(j);
    		int curApples = apple[i];
    		if(curCapacity - curApples > 0) {
    			curCapacity -= curApples;
    			boxes.put(j, curCapacity);
    			i--;
    		} else if(curCapacity - curApples == 0) { 
    			boxes.put(j, 0);
    			i--; j--;
    		} else {
    			boxes.put(j, 0);
    			apple[i] = apple[i] - curCapacity;
    			j--;
    		}
    	}
    	return boxes.size();
    }
}
