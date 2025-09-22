package aq.gym.contests.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountElementsWithMaximumFrequency {

	public static void main(String[] args) {
		int[] nums = {1,2,2,3,1,4};
		System.out.println(new CountElementsWithMaximumFrequency().maxFrequencyElements(nums));
	}

    @SuppressWarnings("unused")
	public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> numberFrequciesMap = new HashMap<>();
        for(int num : nums) {
        	numberFrequciesMap.compute(num, (k, v) -> {
        		if(v == null) {
        			return 1;
        		} else {
        			return v = v + 1;
        		}
        	});
        }
        List<Integer> frequencies = new ArrayList<>(numberFrequciesMap.values());
        frequencies.sort(Comparator.reverseOrder());
        int maxFrequency = frequencies.getFirst();
        int totalFrequency = frequencies.stream()
        		.mapToInt(Integer::intValue)
        		.takeWhile(num -> num == maxFrequency)
        		.reduce(Integer::sum)
        		.getAsInt();
        return totalFrequency;
    }
}
