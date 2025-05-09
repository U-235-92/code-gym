package aq.gym.contests.other;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

	public static void main(String[] args) {
		System.out.println(new FizzBuzz().fizzBuzz(3));
	}

	public List<String> fizzBuzz(int n) {
		final String FIZZ = "Fizz";
		final String BUZZ = "Buzz";
		final String FIZZ_BUZZ = "FizzBuzz";
        List<String> fizzBuzz = new ArrayList<String>(n);
        for(int i = 1; i <= n; i++) {
        	if(i % 3 == 0 && i % 5 == 0)
        		fizzBuzz.add(FIZZ_BUZZ);
        	else if(i % 5 == 0)
        		fizzBuzz.add(BUZZ);
        	else if(i % 3 == 0)
        		fizzBuzz.add(FIZZ);
        	else
        		fizzBuzz.add(String.valueOf(i));
        }
        return fizzBuzz;
    }
}
