package aq.gym.contests.dynamic;

public class NthTribonacciNumber {

	public static void main(String[] args) {
		System.out.println(new NthTribonacciNumber().tribonacci(35));
	}
	
    public int tribonacci(int n) {
    	if(n == 0) {
    		return 0;
    	} else if(n == 1) {
    		return 1;
    	} else if(n == 2) {
    		return 1;
    	} else {    		
    		int[] tribonacci = new int[n + 1];
    		tribonacci[0] = 0; tribonacci[1] = 1; tribonacci[2] = 1;
    		for(int i = 3; i < tribonacci.length; i++) {
    			tribonacci[i] = tribonacci[i - 1] + tribonacci[i - 2] + tribonacci[i - 3];
    		}
    		return tribonacci[n];
    	}
    }
}
