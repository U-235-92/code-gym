package aq.gym.contests.game_theory;

public class NimGame {

//	https://leetcode.com/problems/nim-game/description/
	public static void main(String[] args) {
		int n = 585858;
		System.out.println(new NimGame().canWinNim(n));
	}

    public boolean canWinNim(int n) {
    	if(n <= 3) {
    		return true;
    	}
    	int limit = n % 1000;
    	boolean[] dp = new boolean[limit];
    	dp[0] = true; dp[1] = true; dp[2] = true;
    	for(int i = 3; i < limit; i++) {
    		if(dp[i - 1] == true && dp[i - 2] == true && dp[i - 3] == true) {
    			dp[i] = false;
    		} else {
    			dp[i] = true;
    		}
    	}
    	return dp[limit - 1];
    }
}
