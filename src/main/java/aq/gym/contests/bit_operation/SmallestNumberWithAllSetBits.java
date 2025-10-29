package aq.gym.contests.bit_operation;

public class SmallestNumberWithAllSetBits {

//	https://leetcode.com/problems/smallest-number-with-all-set-bits
	public static void main(String[] args) {
//		int n = 4;
//		int n = 3;
//		int n = 5;
//		int n = 10;
		int n = 1000;
		System.out.println(new SmallestNumberWithAllSetBits().smallestNumber(n));
	}

    public int smallestNumber(int n) {
    	int result = 0, seed = n;
    	while(true) {
    		if((seed & (seed - 1)) == 0 && seed > n) {
    			result = (seed - 1 >= n) ? seed - 1 : seed;
    			break;
    		} else {
    			seed++;
    		}
    	}
    	return result;
    }
}
