package aq.gym.contests.hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

public class Find_X_SumOfAll_K_LongSubarrays_I {

//	https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/description
	public static void main(String[] args) {
//		int[] nums = {1,1,2,2,3,4,2,3};
//		int k = 6, x = 2;
//		int[] nums = {3,8,7,8,7,5};
//		int k = 2, x = 2;
//		int[] nums = {1,2,2};
//		int k = 2, x = 1;
		int[] nums = IntStream.generate(() -> 1 + (int) (Math.random() * (50 - 1) + 1)).limit(50).toArray();
		int k = 2, x = 2;
		System.out.println(Arrays.toString(new Find_X_SumOfAll_K_LongSubarrays_I().findXSum(nums, k, x)));
	}

    public int[] findXSum(int[] nums, int k, int x) {
    	int[] answer = new int[nums.length - k + 1];
    	int idx = 0;
    	for(int i = 0; i < nums.length; i++) {
    		int from = i, to = i + k;
    		calculateXSum(nums, from, to, x, answer, idx);
    		if(to >= nums.length) break;
    		idx++;
    	}
    	return answer;
    }
    
    @SuppressWarnings("unused")
	private void calculateXSum(int[] nums, int from, int to, int x, int[] answer, int idx) {
    	Map<Integer, Integer> mapNumberFreqs = new HashMap<>();
    	for(int i = from; i < to; i++) {
    		int num = nums[i];
    		mapNumberFreqs.compute(num, (key, value) -> (value == null) ? 1 : ++value);
    	}
    	List<Entry<Integer, Integer>> listNumberFreqs = new ArrayList<>(mapNumberFreqs.entrySet());
    	listNumberFreqs.sort((e1, e2) -> {
    		int freqCompare = e2.getValue() - e1.getValue();
    		if(freqCompare == 0) {
    			return e2.getKey() - e1.getKey();
    		}
    		return freqCompare;
    	});
    	for(int j = 0; j < x && j < listNumberFreqs.size(); j++) {
    		answer[idx] += listNumberFreqs.get(j).getKey() * listNumberFreqs.get(j).getValue();
    	}
    }
}
