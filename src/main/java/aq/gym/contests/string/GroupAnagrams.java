package aq.gym.contests.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

//	https://leetcode.com/problems/group-anagrams/description/
	public static void main(String[] args) {
//		String[] strs = {""};
//		String[] strs = {"a"};
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
		List<List<String>> groups = new GroupAnagrams().groupAnagrams(strs);
		groups.forEach(System.out::println);
	}

    public List<List<String>> groupAnagrams(String[] strs) {
    	Map<String, List<String>> groups = new HashMap<>();
    	for(String str : strs) {
    		char[] chrs = str.toCharArray();
    		Arrays.sort(chrs);
    		String base = new String(chrs);
    		groups.compute(base, (k, v) -> {
    			if(v == null) {
    				return new ArrayList<String>(List.of(str));
    			} else {
    				v.add(str);
        			return v;
    			}
    		});
    	}
    	List<List<String>> result = new ArrayList<>();
    	groups.forEach((k, v) -> result.add(v));
    	return result;
    }
}
