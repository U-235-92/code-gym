package aq.gym.contests.array;

import java.util.Arrays;

public class DecodeXORedArray {

//	https://leetcode.com/problems/decode-xored-array/description
	public static void main(String[] args) {
		int[] encoded = {1,2,3};
		int first = 1;
		int[] result = new DecodeXORedArray().decode(encoded, first);
		System.out.println(Arrays.toString(result));
	}

    public int[] decode(int[] encoded, int first) {
        int[] result = new int[encoded.length + 1];
        result[0] = first;
        for(int i = 0; i < encoded.length; i++) {
        	result[i + 1] = encoded[i] ^ first;
        	first = result[i + 1];
        }
        return result;
    }
}
