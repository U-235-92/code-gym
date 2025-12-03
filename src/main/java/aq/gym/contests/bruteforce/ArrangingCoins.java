package aq.gym.contests.bruteforce;

public class ArrangingCoins {

//	https://leetcode.com/problems/arranging-coins/
	public static void main(String[] args) {
		int n = Integer.MAX_VALUE - 1;
		System.out.println(new ArrangingCoins().arrangeCoins(n));
	}

    public int arrangeCoins(int n) {
        int i = 1, answer = 0;
        while(n >= i) {
        	n -= i;
        	i++;
        	answer++;
        }
        return answer;
    }
}
