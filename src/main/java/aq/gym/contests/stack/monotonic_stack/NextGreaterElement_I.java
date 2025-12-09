package aq.gym.contests.stack.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class NextGreaterElement_I {

//	https://leetcode.com/problems/next-greater-element-i/
	public static void main(String[] args) {
		int[] nums1 = {4,1,2};
		int[] nums2 = {1,3,4,2};
		System.out.println(Arrays.toString(new NextGreaterElement_I().nextGreaterElement(nums1, nums2)));
	}

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    	int[] answer = new int[nums1.length];
        Map<Integer, Integer> numberToNextGreaterNumberMap = new HashMap<>();
        Deque<Integer> decreasingNumbersStack = new ArrayDeque<>();
        for(int number : nums2) {
        	if(decreasingNumbersStack.isEmpty()) {
        		decreasingNumbersStack.push(number);
        	} else {
        		if(number > decreasingNumbersStack.peek()) {
        			while(!decreasingNumbersStack.isEmpty() && number > decreasingNumbersStack.peek()) {
        				int poppedNumber = decreasingNumbersStack.pop();
        				numberToNextGreaterNumberMap.put(poppedNumber, number);
        			}
        		}
        		decreasingNumbersStack.push(number);
        	}
        }
        for(int i = 0; i < nums1.length; i++) {
        	int number = nums1[i];
        	int ans = numberToNextGreaterNumberMap.getOrDefault(number, -1);
        	answer[i] = ans;
        }
        return answer;
    }
}
