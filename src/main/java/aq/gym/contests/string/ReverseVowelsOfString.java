package aq.gym.contests.string;

public class ReverseVowelsOfString {

//	https://leetcode.com/problems/reverse-vowels-of-a-string
	public static void main(String[] args) {
		String s = "leetcode";
		System.out.println(new ReverseVowelsOfString().reverseVowels(s));
	}

    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] chrs = s.toCharArray();
        while(i < j) {
        	char a = chrs[i];
        	char b = chrs[j];
        	if(a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u' || a == 'A' || a == 'E' || a == 'I' || a == 'O' || a == 'U') {
        		if(b == 'a' || b == 'e' || b == 'i' || b == 'o' || b == 'u' || b == 'A' || b == 'E' || b == 'I' || b == 'O' || b == 'U') {
        			char tmp = chrs[i];
        			chrs[i] = chrs[j];
        			chrs[j] = tmp;
        			i++; j--;
        			continue;
        		}
        	} else {
        		i++;
        	}
        	if(b == 'a' || b == 'e' || b == 'i' || b == 'o' || b == 'u' || b == 'A' || b == 'E' || b == 'I' || b == 'O' || b == 'U') {
        		if(a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u' || a == 'A' || a == 'E' || a == 'I' || a == 'O' || a == 'U') {
        			char tmp = chrs[i];
        			chrs[i] = chrs[j];
        			chrs[j] = tmp;
        			i++; j--;
        			continue;
        		}
        	} else {
        		j--;
        	}
        }
        return new String(chrs);
    }
}
