package aq.gym.contests.string;

public class IsSubsequence {

//	https://leetcode.com/problems/is-subsequence
	public static void main(String[] args) {
		String s = "leetcode", t = "yyyylyyyyeyyyyyeyytyyyyyyycyyyyyoyyyydyyyyyyyyyyyyeyyyyyyy";
		System.out.println(new IsSubsequence().isSubsequence(s, t));
	}

    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()) {
        	char a = s.charAt(i);
        	char b = t.charAt(j);
        	if(a == b) {
        		i++;
        	}
        	j++;
        }
        return i >= s.length();
    }
}
