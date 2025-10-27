package aq.gym.contests.bit_operation;

public class PowerOfTwo {

//	https://leetcode.com/problems/power-of-two/
	public static void main(String[] args) {
//		int n = (int) Math.pow(2, 31) - 1;
		int n = -5;
		System.out.println(new PowerOfTwo().isPowerOfTwo(n));
	}

    public boolean isPowerOfTwo(int n) {
    	switch (n) {
	    	case 0:
	    	case Integer.MIN_VALUE:
	    		return false;
	    	case 1:
	    		return true;
	    	default:
	    		return (n & (n - 1)) == 0;
    	}
    }
}
