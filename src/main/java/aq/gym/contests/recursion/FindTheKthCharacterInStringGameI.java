package aq.gym.contests.recursion;

public class FindTheKthCharacterInStringGameI {
	
//	https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/description
	public static void main(String[] args) {
		int k = Integer.valueOf(args[0]);
		System.out.println(new FindTheKthCharacterInStringGameI().kthCharacter(k));
	}

	public char kthCharacter(int k) {
        return kthCharacter0(k, "a");
    }
	
	private char kthCharacter0(int k, String word) {
		if(word.length() >= k) {
			return word.charAt(k - 1);
		}
		char[] chrs = word.toCharArray();
		for(int i = 0; i < chrs.length; i++) {
			int code = (chrs[i] + 1) % (97 + 26);
			chrs[i] = (char) (code);
		}
		return kthCharacter0(k, word + new String(chrs));
	}
}
