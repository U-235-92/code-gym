package aq.gym.contests.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MaximumScoreWordsFormedByLetters {

//	https://leetcode.com/problems/maximum-score-words-formed-by-letters/description/
	public static void main(String[] args) {
//		Case#1
		String[] words = {"dog","cat","dad","good"};
		char[] letters = {'a','a','c','d','d','d','g','o','o'};
		int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
		
//		Case#2
//		String[] words = {"xxxz","ax","bx","cx"};
//		char[] letters = {'z','a','b','c','x','x','x'};
//		int[] score = {4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10};
		
//		Case#3
//		String[] words = {"leetcode"};
//		char[] letters = {'l','e','t','c','o','d'};
//		int[] score = {0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0};
		System.out.println(new MaximumScoreWordsFormedByLetters().maxScoreWords(words, letters, score));
		
	}

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<Character, Integer> letterFrequencyMap = getLetterFrequencyMap(letters);
        Set<String> noValidWords = getNoValidWords(words, letterFrequencyMap);
        if(noValidWords.size() == words.length) {
        	return 0;
        } else {
        	ArrayDeque<String> wordsListQueue = new ArrayDeque<>(List.of(words));
        	wordsListQueue.removeAll(noValidWords);
        	noValidWords = null;
        	Map<String, Integer> wordScoreHash = new HashMap<>();
        	Map<Character, Integer> letterScoreMap = getLetterScoreMap(score);
        	PriorityQueue<Integer> maxWordScores = new PriorityQueue<>(Comparator.reverseOrder());
        	calculateMaxScore(wordScoreHash, letterFrequencyMap, letterScoreMap, wordsListQueue, new ArrayList<String>(), maxWordScores, 0, "");
        	return maxWordScores.poll();
        }
    }
    
    private Map<Character, Integer> getLetterFrequencyMap(char[] letters) {
    	Map<Character, Integer> lettersMap = new HashMap<>();
        for(char letter : letters) {
        	lettersMap.compute(letter, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return lettersMap;
    }
    
    private Set<String> getNoValidWords(String[] words, Map<Character, Integer> letterFrequencyMap) {
    	Set<String> noValidWords = new HashSet<>();
    	for(String word : words) {
    		for(int i = 0; i < word.length(); i++) {
    			if(!letterFrequencyMap.containsKey(word.charAt(i))) {
    				noValidWords.add(word);
    				break;
    			}
    		}
    	}
    	return noValidWords;
    }
    
    private Map<Character, Integer> getLetterScoreMap(int[] score) {
    	Map<Character, Integer> lettersMap = new HashMap<>();
    	for(int i = 0; i < score.length; i++) {
    		char ch = (char) (i + 97);
    		lettersMap.put(Character.valueOf(ch), score[i]);
    	}
    	return lettersMap;
    }
    
    private void calculateMaxScore( 
    		Map<String, Integer> wordScoreHash, 
    		Map<Character, Integer> letterFrequencyMap, 
    		Map<Character, Integer> letterScoreMap, 
    		ArrayDeque<String> wordsToResearch,
    		List<String> currentWordList,
    		PriorityQueue<Integer> maximalScores,
    		int scoreAccumulator,
    		String wordAccumulator) {
    	if(wordsToResearch.isEmpty()) {
    		maximalScores.add(scoreAccumulator);
    		return;
    	}
    	String currWord = wordsToResearch.poll();
//    	int currWordScore = 0;
//		if(wordScoreHash.get(currWord) == null) {    		
//			currWordScore = getWordScore(currWord, letterFrequencyMap, letterScoreMap);
//			if(currWordScore != 0) {				
//				wordScoreHash.put(currWord, currWordScore);
//			}
//		} else {
//			currWordScore = wordScoreHash.get(currWord);
//		}
		for(int i = 0; i < currentWordList.size(); i++) {
			int currWordScore = getWordScore(currWord, letterFrequencyMap, letterScoreMap);
			int nextWordScore = getWordScore(currentWordList.get(i), letterFrequencyMap, letterScoreMap);
			int totalWordScore = currWordScore + nextWordScore;
			List<String> tmp = new ArrayList<>(wordsToResearch);
			calculateMaxScore(wordScoreHash, letterFrequencyMap, letterScoreMap, wordsToResearch, tmp, maximalScores, totalWordScore, wordAccumulator);
			
		}
		currentWordList = new ArrayList<>(wordsToResearch);
		maximalScores.add(scoreAccumulator);
    }
    
    private int getWordScore(String word, 
    		Map<Character, Integer> letterFrequencyMap, 
    		Map<Character, Integer> letterScoreMap) {
    	int score = 0, i = 0;
    	boolean isShouldPutLettersBack = false;
    	for(i = 0; i < word.length(); i++) {
    		char letter = word.charAt(i);
    		score += letterScoreMap.get(letter);
    		if(letterFrequencyMap.containsKey(letter)) {    			
    			int freq = letterFrequencyMap.get(letter);
    			if(freq == 0) {
    				score = 0;
    				isShouldPutLettersBack = true;
    				break;
    			} else {    				
    				letterFrequencyMap.put(letter, freq - 1);
    			}
    		} else {
    			score = 0;
    			break;
    		}
    	}
    	if(isShouldPutLettersBack) {
    		putWordLettersBack(word, i, letterFrequencyMap);
    	}
    	return score;
    }
    
    private void putWordLettersBack(String word, int limit, Map<Character, Integer> letterFrequencyMap) {
    	for(int i = 0; i < limit; i++) {
    		char ch = word.charAt(i);
    		int freq = letterFrequencyMap.get(ch);
    		letterFrequencyMap.put(ch, freq + 1);
    	}
    }
}
