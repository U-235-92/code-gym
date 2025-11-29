package aq.gym.contests.dynamic;

public class LongestCommonSubsequence {

//	https://leetcode.com/problems/longest-common-subsequence/
	public static void main(String[] args) {
//		String text1 = "abcde", text2 = "ace";
//		String text1 = "abc", text2 = "def";
//		String text1 = "abc", text2 = "abc";
//		String text1 = "abcde", text2 = "a";
//		String text1 = "short", text2 = "ports";
		String text1 = "jijiijjpji", text2 = "jjji";
		System.out.println(new LongestCommonSubsequence().longestCommonSubsequence(text1, text2));
	}

    public int longestCommonSubsequence(String text1, String text2) {
    	if(text1.equals(text2)) {
    		return text1.length();
    	} else if(text1.length() == 1) {
    		if(text2.contains(text1)) {
    			return 1;
    		} else {
    			return 0;
    		}
    	} else if(text2.length() == 1) {
    		if(text1.contains(text2)) {
    			return 1;
    		} else {
    			return 0;
    		}
    	} else {
    		int row = text1.length(), column = text2.length();
    		int[][] dp = new int[row][column];
    		for(int i = 0; i < row; i++) {
    			for(int j = 0; j < column; j++) {
    				char ch1 = text1.charAt(i), ch2 = text2.charAt(j);
    				if(isStartPosition(i, j)) {
    					if(isCharactersEquals(ch1, ch2)) {
    						updateDP(i, j, 1, dp);
    					}
    				} else if(isZeroRow(i, j)) {
    					if(isCharactersEquals(ch1, ch2)) {    						
    						updateDP(i, j, 1, dp);
    					} else {
    						updateDP(i, j, dp[i][j - 1], dp);
    					}
    				} else if(isZeroColumn(i, j)) { 
    					if(isCharactersEquals(ch1, ch2)) {
    						updateDP(i, j, 1, dp);
    					} else {
    						updateDP(i, j, dp[i - 1][j], dp);
    					}
    				} else {
    					if(isCharactersEquals(ch1, ch2)) {
    						updateDP(i, j, dp[i - 1][j - 1] + 1, dp);
    					} else {    						
    						updateDP(i, j, Integer.max(dp[i][j - 1], dp[i - 1][j]), dp);
    					}
    				}
    			}
    		}
    		return dp[row - 1][column - 1];
    	}
    }
    
    private boolean isCharactersEquals(char ch1, char ch2) {
    	return ch1 == ch2;
    }
    
    private boolean isStartPosition(int i, int j) {
    	return i == 0 && j == 0;
    }
    
    private boolean isZeroRow(int i, int j) {
    	return i == 0 && j > 0;
    }
    
    private boolean isZeroColumn(int i, int j) {
    	return i > 0 && j == 0;
    }
    
    private void updateDP(int i, int j, int value, int[][] dp) {
    	dp[i][j] = value;
    }
}
