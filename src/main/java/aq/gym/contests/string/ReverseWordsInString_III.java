package aq.gym.contests.string;

public class ReverseWordsInString_III {

	public static void main(String[] args) {
		String orig = "a good   example";
		String reverse = new ReverseWordsInString_III().reverseWords(orig);
		System.out.println(orig);
		System.out.println(reverse);
		System.out.println("Orig length == reverse length? " + (orig.length() == reverse.length()));
	}

	public String reverseWords(String s) {
        char[] chrs = s.toCharArray();
        int i = 0, j = 0;
        for(; i < chrs.length;) {
        	while(j < chrs.length && chrs[j] != ' ') {
        		j++;
        	}
        	int left = i, right = j - 1;
        	while(left < right) {
        		char tmp = chrs[left];
        		chrs[left] = chrs[right];
        		chrs[right] = tmp;
        		left++; right--;
        	}
        	i = j + 1;
        	j++;
        }
        return new String(chrs);
    }
}
