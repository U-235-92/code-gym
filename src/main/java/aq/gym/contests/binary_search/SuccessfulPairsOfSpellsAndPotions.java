package aq.gym.contests.binary_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotions {

//	https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
	public static void main(String[] args) {
		testSmallNumbers();
		testBigNumbers();
	}
	
	private static void testSmallNumbers() {
//		int[] spells = {1}, potions = {7};
//		int success = 7;
		int[] spells = {5,1,3}, potions = {1,2,3,4,5};
		int success = 7;
		System.out.println(Arrays.toString(new SuccessfulPairsOfSpellsAndPotions().successfulPairs(spells, potions, success)));
	}

	private static void testBigNumbers() {
		File file = new File("src/main/java/aq/gym/contests/binary_search/SuccessfulPairsOfSpellsAndPotionsTestCase.txt");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			int[] spells = null; 
			int[] potions = null;
			long success = 0L;
			for(int i = 0; i < 3; i++) {
				String line = br.readLine();
				switch (i) {
				case 0:
					spells = Arrays.stream(line.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
					break;
				case 1:
					potions = Arrays.stream(line.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
					break;
				case 2:
					success = Long.parseLong(line);
					break;
				}
			}
			int[] pairs = new SuccessfulPairsOfSpellsAndPotions().successfulPairs(spells, potions, success);
			System.out.println(Arrays.toString(pairs));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] pairs = new int[spells.length];
        successfulPairs(spells, potions, pairs, success);
        return pairs;
    }
    
    private void successfulPairs(int[] spells, int[] potions, int[] pairs, long success) {
    	Arrays.sort(potions);
    	for(int i = 0; i < spells.length; i++) {
    		int spell = spells[i];
    		pairs[i] = successfulPairs(potions, spell, success); 
    	}
    }
    
    private int successfulPairs(int[] potions, int spell, long success) {
    	int left = 0, right = potions.length - 1;
    	int successfulPairs = 0;
    	while(left <= right) {
    		int mid = left + (right - left) / 2;
    		long magic = 1L * potions[mid] * spell; 
    		if(magic >= success) {
    			successfulPairs += (right + 1 - mid);
    			right = mid - 1;
    		} else {
    			left = mid + 1;
    		}
    	}
    	return successfulPairs;
    }
}
