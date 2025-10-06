package aq.gym.contests.numbers;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MissingNumber {

//	https://leetcode.com/problems/missing-number/
	public static void main(String[] args) {
		int[] nums = {2,1,0};
		System.out.println(new MissingNumber().missingNumber(nums));
	}

    public int missingNumber(int[] nums) {
        int totalMax = IntStream.iterate(0, num -> num + 1).limit(nums.length + 1).sum();
        int totalCur = Arrays.stream(nums).sum();
        int missing = totalMax - totalCur;
        return missing;
    }
}
