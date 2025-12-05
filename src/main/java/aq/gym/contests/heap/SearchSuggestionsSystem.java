package aq.gym.contests.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SearchSuggestionsSystem {
	
//	https://leetcode.com/problems/search-suggestions-system
	public static void main(String[] args) {
//		String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
//		String searchWord = "mouse";
		String[] products = {"havana"};
		String searchWord = "havana";
		List<List<String>> suggestedProducts = new SearchSuggestionsSystem().suggestedProducts(products, searchWord);
		suggestedProducts.forEach(System.out::println);
	}

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    	List<List<String>> suggestedProducts = new ArrayList<>();
        PriorityQueue<String> lexicographicallyPQ = getLexicographicallyPQ(products);
        for(int p = 1; p <= searchWord.length(); p++) {
        	List<String> productWords = new ArrayList<>();
        	List<String> surviveWords = new ArrayList<>(); 
        	String prefix = searchWord.substring(0, p);
        	while(productWords.size() < 3 && !lexicographicallyPQ.isEmpty()) {
        		String word = lexicographicallyPQ.poll();
        		if(word.startsWith(prefix)) {
        			productWords.add(word);
        			surviveWords.add(word);
        		}
        	}
        	suggestedProducts.add(productWords);
        	if(!surviveWords.isEmpty()) {
        		lexicographicallyPQ.addAll(surviveWords);
        	}
        }
        return suggestedProducts;
    }
    
    private PriorityQueue<String> getLexicographicallyPQ(String[] products) {
    	PriorityQueue<String> lexicographicallyPQ = new PriorityQueue<>();
        for(int i = 0; i < products.length; i++) {
        	lexicographicallyPQ.offer(products[i]);
        }
        return lexicographicallyPQ;
    }
}
