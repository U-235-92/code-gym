package aq.gym.contests.string;

public class MergeStringsAlternately {

//	https://leetcode.com/problems/merge-strings-alternately	
	public static void main(String[] args) {
		String word1 = "a", word2 = "p";
		System.out.println(new MergeStringsAlternately().mergeAlternately(word1, word2));
	}

    public String mergeAlternately(String word1, String word2) {
    	char[] aChrs = word1.toCharArray();
    	char[] bChrs = word2.toCharArray();
        char[] merge = new char[aChrs.length + bChrs.length];
        int i = 0, j = 0, k = 0;
        while(i < aChrs.length && j < bChrs.length) {
        	if(k % 2 == 0) {
        		merge[k++] = aChrs[i++];
        	} else {
        		merge[k++] = bChrs[j++];
        	}
        }
        while(i < aChrs.length) {
        	merge[k++] = aChrs[i++];
        }
        while(j <bChrs.length) {
        	merge[k++] = bChrs[j++];
        }
        return new String(merge);
    }
}
