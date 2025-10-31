package aq.gym.contests.math;

public class ReverseInteger {

//	https://leetcode.com/problems/reverse-integer/description/
	public static void main(String[] args) {
//		int a = Integer.MAX_VALUE;
//		int b = 21;
//		System.out.println(Integer.toBinaryString(a));
//		System.out.println(Integer.toBinaryString(b));
//		System.out.println((a & Integer.MAX_VALUE) == a);
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(1534236469);
//		int x = 1534236469;
		int x = -123;
		System.out.println(new ReverseInteger().reverse(x));
	}

    public int reverse(int x) {
    	int sign = (x < 0) ? -1 : 1;
    	int reverse = 0, orig = Math.abs(x);
    	while(orig > 0) {
    		if((reverse & Integer.MAX_VALUE) != reverse) {
    			reverse = 0;
    			break;
    		}
    		if((reverse * 10) / 10 != reverse) {
    			reverse = 0;
    			break;
    		}
    		reverse = (reverse * 10) + (orig % 10);
    		orig /= 10;
    	}
    	return reverse * sign;
    }
}
