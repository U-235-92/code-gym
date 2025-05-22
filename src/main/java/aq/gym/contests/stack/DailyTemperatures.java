package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {

	public static void main(String[] args) {
		int[] temperatures = {73,74,75,71,69,72,76,73};
		temperatures[temperatures.length - 1] = 100;
		int[] output = new DailyTemperatures().dailyTemperatures(temperatures);
		System.out.println(Arrays.toString(output));
	}
	
	public int[] dailyTemperaturesSlow(int[] temperatures) {
		if(temperatures.length == 1) {
			return new int[] {0};
		}
		 int[] output = new int[temperatures.length];
		 for(int i = 0; i < temperatures.length; i++) {
			 int count = 0; boolean isVarmer = false;
			 for(int j = i + 1; j < temperatures.length; j++) {
				 if(temperatures[j] > temperatures[i]) {
					 count++;
					 isVarmer = true;
					 break;
				 } else {
					 count++;
				 }
			 }
			 if(isVarmer) {
				 output[i] = count;
				 isVarmer = false;
			 }
		 }
		 return output;
	}
	
	public int[] dailyTemperatures(int[] temperatures) {
		int[] results = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                results[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return results;
    }
}
