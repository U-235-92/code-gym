package aq.gym.contests.recursion;

public class Pow {

	public static void main(String[] args) {
		int n = Integer.MAX_VALUE - 1;
		double x = 100.00;
		System.out.println(Math.pow(x, n));
		System.out.println(new Pow().myPow(x, n));
	}
	
    public double myPow(double x, int n) {
    	if(n == 0) {    		
    		return 1.0;
    	} else if(n >= 1) {    		
    		return powLogWay(x, n);
    	} else if(n <= -1) {
    		return 1 / powLogWay(x, n);
    	}
        return 0;
    }
    
	private double powLogWay(double x, int n) {
		if(n == 0)
			return 1;
		double halfPow = powLogWay(x, n / 2);
		if((n & 1) == 0) {
			return halfPow * halfPow;
		} else {
			return halfPow * halfPow * x;
		}
	}
	
	@SuppressWarnings("unused")
	private double powBruteForceWay(double x, double mul, int currPow, final int baseCase) {
		if(currPow == baseCase)
			return x;
		return powBruteForceWay(x * mul, mul, (baseCase == -1) ? currPow + 1 : currPow - 1, baseCase);
	}
}
