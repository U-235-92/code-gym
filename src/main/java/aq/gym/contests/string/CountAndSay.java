package aq.gym.contests.string;

public class CountAndSay {

//	https://leetcode.com/problems/count-and-say/
	public static void main(String[] args) {
		System.out.println(new CountAndSay().countAndSay(30));
	}

    public String countAndSay(int n) {
    	String[] answer = new String[1];
    	countAndSay(n, 1, "1", answer);
    	return answer[0];
    }
    
    private void countAndSay(final int limit, int iteration, String curr, String[] answer) {
    	if(iteration >= limit) {
    		answer[0] = curr;
    		return;
    	}
    	curr = countAndSay(curr);
    	countAndSay(limit, iteration + 1, curr, answer);
    }
    
    private String countAndSay(String str) {
    	String result = "";
		char curr = str.charAt(0), prev = str.charAt(0);
		int i = 0, counter = 0;
		while(i < str.length()) {
			curr = str.charAt(i);
			if(curr != prev) {
				result += counter + "" + prev + "";
				counter = 1;
			} else {
				counter++;
			}
			i++;
			prev = str.charAt(i - 1);
		}
		result += counter + "" + curr + "";
		return result;
    }
}
