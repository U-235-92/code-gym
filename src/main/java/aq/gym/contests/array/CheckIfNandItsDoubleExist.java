package aq.gym.contests.array;

import java.util.HashMap;
import java.util.Map;

public class CheckIfNandItsDoubleExist {

	public static void main(String[] args) {
		int[] arr = {6,3};
		boolean isExist = new CheckIfNandItsDoubleExist().checkIfExist(arr);
		System.out.println(isExist);
	}

	public boolean checkIfExist(int[] arr) {
		if(arr.length < 2) {
			return false;
		}
        Map<Integer, Boolean> checkMap = new HashMap<>();
        checkMap.put(arr[0], false);
        for(int i = 1; i < arr.length; i++) {
        	if(checkMap.containsKey(2 * arr[i])) {
        		return true;
        	}
        	if(arr[i] % 2 == 0) {
        		if(checkMap.containsKey(arr[i] / 2)) {
            		return true;
            	}
        	}
        	checkMap.put(arr[i], false);
        }
        return false;
    }
}
