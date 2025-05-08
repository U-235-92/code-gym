package aq.gym.contests.string;

public class IndexOfFirstOccurenceString {

	public static void main(String[] args) {
		String haystack = "leetcode";
		String needle = "leeto";
		int index = new IndexOfFirstOccurenceString().strStr(haystack, needle);
		System.out.println(index);
	}

	public int strStr(String haystack, String needle) {
		int index = -1;
		index = haystack.indexOf(needle);
		return index;
	}
}
