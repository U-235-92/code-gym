package aq.gym.contests.bit_operation;

import java.util.Arrays;

public class CountingBits {

//	https://leetcode.com/problems/counting-bits/
	public static void main(String[] args) {
//		System.out.println(Integer.toBinaryString(-1));
//		System.out.println(Integer.toBinaryString(2 >>> 2));
//		System.out.println((2 & 1) == 1);
		int n = 5;
		System.out.println(Arrays.toString(new CountingBits().countBits(n)));
	}

    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for(int i = n; i >= 0; i--) {
        	int num = i;
        	int countOnes = 0;
        	while(num != 0) {
        		if((num & 1) == 1) {
        			countOnes++;
        		}
        		num = num >>> 1;
        	}
        	result[i] = countOnes;
        }
        return result;
    }
}
