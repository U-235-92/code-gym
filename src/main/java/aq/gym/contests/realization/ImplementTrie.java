package aq.gym.contests.realization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ImplementTrie {

	class Trie {

		private Map<Character, Set<String>> trieMap;
		
	    public Trie() {
	        trieMap = new HashMap<>();
	    }
	    
	    @SuppressWarnings("unused")
		public void insert(String word) {
	    	trieMap.compute(word.charAt(0), (k, v) -> {
	    		if(v == null) {
	    			Set<String> words = new HashSet<>();
	    			words.add(word);
	    			return words;
	    		} else {
	    			v.add(word);
	    			return v;
	    		}
	    	});
	    }
	    
	    public boolean search(String word) {
	        if(trieMap.containsKey(word.charAt(0))) {
	        	return trieMap.get(word.charAt(0)).contains(word);
	        } else {
	        	return false;
	        }
	    }
	    
	    public boolean startsWith(String prefix) {
	    	if(trieMap.containsKey(prefix.charAt(0))) {
	        	return trieMap.get(prefix.charAt(0)).stream().anyMatch(word -> word.startsWith(prefix));
	        } else {
	        	return false;
	        }
	    }
	}
	
//	https://leetcode.com/problems/implement-trie-prefix-tree
	public static void main(String[] args) {
		Trie trie = new ImplementTrie().new Trie();
		trie.insert("apple");
		System.out.println(trie.search("apple"));
		System.out.println(trie.search("app"));
		System.out.println(trie.startsWith("app"));
		trie.insert("app");
		System.out.println(trie.search("app"));
	}
}
