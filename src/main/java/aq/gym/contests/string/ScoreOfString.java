package aq.gym.contests.string;

public class ScoreOfString {

//	https://leetcode.com/problems/score-of-a-string/description/
	public static void main(String[] args) {
		String s = "hello";
		System.out.println(new ScoreOfString().scoreOfString(s));
	}

    public int scoreOfString(String s) {
    	int result = 0;
        for(int i = 0; i < s.length() - 1; i++) {
        	int tmp = Math.abs((int) s.charAt(i) - (int) s.charAt(i + 1));
        	result += tmp;
        }
        return result;
    }
}
