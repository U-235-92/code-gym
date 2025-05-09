package aq.gym.contests.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class PlusOne {

	public static void main(String[] args) {
		int[] nums = {3,9,3,9};
		int[] plusOne = new PlusOne().plusOne(nums);
		System.out.println(Arrays.toString(plusOne));
	}

	public int[] plusOne(int[] digits) {
		if(digits[digits.length - 1] == 9) {
			if(digits.length == 1) {
				return new int[]{1, 0};
			} else {
				Deque<Integer> stack = new ArrayDeque<>();
				int carry = 1;
				stack.push(0);
				for(int i = digits.length - 2; i >= 0; i--) {
					int num = (digits[i] + carry) % 10;
					carry = (digits[i] + carry) / 10;
					stack.push(num);
				}
				if(carry == 1) {
					stack.push(carry);
				}
				int[] result = new int[stack.size()];
				int i = 0;
				while(!stack.isEmpty() && i < result.length) {
					result[i++] = stack.pop();
				}
				return result;
			}
		} else {
			digits[digits.length - 1] += 1;
			return digits;
		}
    }
}
