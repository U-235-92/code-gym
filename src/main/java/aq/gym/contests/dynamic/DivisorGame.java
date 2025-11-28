package aq.gym.contests.dynamic;

public class DivisorGame {

//	https://leetcode.com/problems/divisor-game/
	public static void main(String[] args) {
		System.out.println(new DivisorGame().divisorGame(999));
	}
	
    public boolean divisorGame(int n) {
    	if(n == 1) {
    		return false;
    	} else if(n == 2) {
    		return true;
    	} else {    		
    		boolean[] dp = new boolean[n + 1];
    		dp[0] = false; dp[1] = false; dp[2] = true;
    		for(int i = 3; i < dp.length; i++) {
    			dp[i] = dp[i - 2];
    		}
    		return dp[n];
    	}
    }
}
