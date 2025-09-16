package aq.gym.contests.numbers;

public class Palindrome {

	public static void main(String[] args) {
		System.out.println(new Palindrome().isPalindrome(-121));
	}

	public boolean isPalindrome(int x) {
		if(x < 0)
			return false;
		int palindrome = 0;
		int orig = x;
		while(x != 0) {
			palindrome = (palindrome * 10) + (x % 10);
			x /= 10;
		}
		return palindrome == orig;
    }
}
