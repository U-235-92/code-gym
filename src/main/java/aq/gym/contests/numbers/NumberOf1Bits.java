package aq.gym.contests.numbers;

import java.util.Arrays;

public class NumberOf1Bits {

//	https://leetcode.com/problems/number-of-1-bits/description/
	public static void main(String[] args) {
		int number = 11;
		System.out.println(new NumberOf1Bits().hammingWeight(number));
		System.out.println(new NumberOf1Bits().hammingWeightAsBinaryString(number));
		System.out.println(new NumberOf1Bits().hammingWeightAsBitOperation(number));
	}

    public int hammingWeight(int n) {
        return hammingWeigthRecursive(n, 0);
    }
    
    private int hammingWeigthRecursive(int number, int weight) {
    	if(number == 1 && weight == 0) return 1;
    	if(number / 2 == 0) return weight;
    	if(number / 2 == 1) weight++;
    	if(number % 2 == 1) weight++;
    	number = number / 2;
    	return hammingWeigthRecursive(number, weight);
    }
    
    private int hammingWeightAsBinaryString(int number) {
    	return (int) Arrays.stream(Integer.toBinaryString(number).split("")).filter(n -> n.equals("1")).count();
    }
    
    private int hammingWeightAsBitOperation(int number) {
    	int shift = 1, count = 0;
    	for(int i = 0; i < 32; i++) {
    		int guess = number & shift;
    		if(guess != 0) {
    			count++;
    		}
    		shift = shift << 1;
    	}
    	return count;
    }
}
