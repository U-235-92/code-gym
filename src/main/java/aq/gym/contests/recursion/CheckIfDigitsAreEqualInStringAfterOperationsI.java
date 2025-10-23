package aq.gym.contests.recursion;

public class CheckIfDigitsAreEqualInStringAfterOperationsI {

//	https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i
	public static void main(String[] args) {
		String s = "1520083175259392049333972290335909930032827947977564513205140242239973690740649148909054875048482640";
		System.out.println(new CheckIfDigitsAreEqualInStringAfterOperationsI().hasSameDigits(s));
	}

    public boolean hasSameDigits(String s) {
        if(s.length() == 2) {
        	return s.charAt(0) == s.charAt(1);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length() - 1; i++) {
        	int a = Character.digit(s.charAt(i), 10);
        	int b = Character.digit(s.charAt(i + 1), 10);
        	int c = (a + b) % 10;
        	sb.append(c);
        }
        return hasSameDigits(sb.toString());
    }
}
