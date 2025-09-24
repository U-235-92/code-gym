package aq.gym.contests.numbers;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
	
//	https://leetcode.com/problems/fraction-to-recurring-decimal
	public static void main(String[] args) {
		if(args.length == 0) {			
			int numerator = -2147483648; //2147483647
			int denominator = -1; //37
			System.out.println(new FractionToRecurringDecimal().fractionToDecimal(numerator, denominator));
		} else {
			int numerator = Integer.valueOf(args[0]);
			int denominator = Integer.valueOf(args[1]);
			System.out.println(new FractionToRecurringDecimal().fractionToDecimal(numerator, denominator));
		}
	}
	
    public String fractionToDecimal(int numerator, int denominator) {
    	if((long) (numerator % denominator) == 0L) {
    		long a = numerator, b = denominator;
    		return (a / b) + "";
    	}
    	if(numerator > 0 && denominator > 0 || numerator < 0 && denominator < 0) {
    		String result = solution(numerator, denominator, new StringBuilder(), new HashMap<Long, Integer>(), 0).toString();
        	return result;
    	} else if(numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) {
    		String result = solution(numerator, denominator, new StringBuilder(), new HashMap<Long, Integer>(), 0).toString();
        	return "-" + result;
    	} else {
    		return "0";
    	}
    }
    
    private String solution(long numerator, long denominator, StringBuilder fractionBuilder, Map<Long, Integer> reapitings, int increment) {
    	numerator = (long) Math.abs(numerator);
    	denominator = (long) Math.abs(denominator);
    	if(reapitings.keySet().contains((long) (numerator % denominator))) {
    		fractionBuilder.append((long) (numerator / denominator));
    		StringBuilder a = new StringBuilder(fractionBuilder.subSequence(0, fractionBuilder.indexOf("."))); // A part of a number before '.'
    		StringBuilder b = new StringBuilder(fractionBuilder.substring(fractionBuilder.indexOf(".") + 1)); // A part of a number after '.'
    		int startBracketIdx = reapitings.get((long) (numerator % denominator));
    		StringBuilder result = new StringBuilder();
    		result.append(a)
    			.append(".")
    			.append(b.substring(0, startBracketIdx))
    			.append("(")
    			.append(b.substring(startBracketIdx))
    			.append(")");	
    		return result.toString();
    	}
    	if(fractionBuilder.length() >= 10000) {
    		return fractionBuilder.toString();
    	}
    	if((long) (numerator % denominator) == 0L) {
        	long number = (long) (numerator / denominator);
    		fractionBuilder.append(number);
    		return fractionBuilder.toString();
    	} 
    	if(numerator < denominator && fractionBuilder.isEmpty()) {
    		fractionBuilder.append("0.");
    		if(reapitings.keySet().contains((long) (numerator % denominator))) { 
    			return fractionBuilder.toString();
    		} 
    		reapitings.put((long) (numerator % denominator), increment);
    		return solution((long) (numerator * 10), denominator, fractionBuilder, reapitings, ++increment);
    	}
    	if(numerator > denominator && fractionBuilder.isEmpty()) {
        	long number = (long) (numerator / denominator);
    		fractionBuilder.append(number + ".");
    		if(reapitings.keySet().contains((long) (numerator % denominator))) {
    			return fractionBuilder.toString();
    		}
    		reapitings.put((long) (numerator % denominator), increment);
    		numerator = (long) (numerator % denominator);
    		return solution((long) (numerator * 10), denominator, fractionBuilder, reapitings, ++increment);
    	}
    	long number = (long) (numerator / denominator);
		if(reapitings.keySet().contains((long) (numerator % denominator))) {
			return fractionBuilder.toString();
		}
		reapitings.put((long) (numerator % denominator), increment);
		fractionBuilder.append(number);
		numerator = (long) (numerator % denominator);
		return solution((long) (numerator * 10), denominator, fractionBuilder, reapitings, ++increment);
    }
}
