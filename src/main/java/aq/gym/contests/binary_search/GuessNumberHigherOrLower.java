package aq.gym.contests.binary_search;

public class GuessNumberHigherOrLower {

//	https://leetcode.com/problems/guess-number-higher-or-lower/description
	public static void main(String[] args) {
		System.out.println(new GuessNumberHigherOrLower().guessNumber(10));
	}

	public int guess(int num) {
		int pick = 4;
		if(num == pick) return 0;
		else if(num > pick) return -1;
		else return 1;
	}
	
    public int guessNumber(int n) {
        int left = 1, right = n, mid = 0;
        while(left <= right) {
        	mid = left + (right - left) / 2;
        	if(guess(mid) == 0) {
        		return mid;
        	} else if(guess(mid) == 1) {
        		left = mid + 1;
        	} else {
        		right = mid - 1;
        	}
        } 
        return n;
    }
}
