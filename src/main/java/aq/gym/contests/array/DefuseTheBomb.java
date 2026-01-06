package aq.gym.contests.array;

import java.util.Arrays;

public class DefuseTheBomb {

	public static void main(String[] args) {
//		int[] code = {5,7,1,4}; // 12,10,16,13
//		int k = 3;
//		int[] code = {1,2,3,4}; // 0,0,0,0
//		int k = 0;
		int[] code = {86,38,89,32,71,76,29,32,7,19,81,65,41,56,89,68,42,15,5,77,88,9,94,63,94,39,35,59,25,42,89,77,75,50,89,79,95,67,48,66,60,50,100,99,84,47,10,3,26,83,94,71,33,55,63,25,20,87,76,98,93,53,94,63,25,45,75,97,64,49,29,85,91,68,84,48,7,16,91,37,41,77,41,14,77,19,36,100,33,47,26,23,58,82,65,13,5,72,80}; // 12,5,6,13
		int k = -65;
		System.out.println(Arrays.toString(new DefuseTheBomb().decrypt(code, k)));
	}

    public int[] decrypt(int[] code, int k) {
    	if(k == 0) {
    		for(int i = 0; i < code.length; i++) {
    			code[i] = 0;
    		}
    		return code;
    	} else if(k > 0) {
    		int[] result = new int[code.length];
    		for(int i = 0; i < result.length; i++) {
    			for(int j = i + 1; j < i + k + 1; j++) {
    				result[i] += code[j % code.length];
    			}
    		}
    		return result;
    	} else {
    		int[] result = new int[code.length];
    		for(int i = 0; i < result.length; i++) {
    			for(int j = i - Math.abs(k); j < i; j++) {
    				result[i] += code[(j + code.length) % code.length];
    			}
    		}
    		return result;
    	}
    }
}
