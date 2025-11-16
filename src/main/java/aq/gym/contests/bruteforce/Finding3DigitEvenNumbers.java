package aq.gym.contests.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Finding3DigitEvenNumbers {

//	https://leetcode.com/problems/finding-3-digit-even-numbers/description/
	public static void main(String[] args) {
		int[] digits = IntStream.generate(() -> (int) (Math.random() * 10)).limit(100).toArray();
		System.out.println(Arrays.toString(new Finding3DigitEvenNumbers().findEvenNumbers(digits)));
	}

    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> combinations = new HashSet<>();
        List<Integer> accumulator = new ArrayList<>();
        for(int i = 0; i < digits.length; i++) {
        	for(int j = 0; j < digits.length; j++) {
        		for(int k = 0; k < digits.length; k++) {
        			if(i != j && j != k && i != k) {
        				int number = digits[i] * 100 + digits[j] * 10 + digits[k];
        				if(number >= 100 && number <= 999 && number % 2 == 0) {        					
        					combinations.add(number);
        				}
        			}
        		}
        	}
        }
        accumulator.addAll(combinations);
        accumulator.sort(Comparator.naturalOrder());
        return accumulator.stream().mapToInt(Integer::intValue).toArray();
    }
}
