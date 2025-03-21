package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class PrimeFactorsOfNumber {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<Long> factors = new LinkedList<>();
			long number = Long.valueOf(br.readLine());
			for(long factor = 2; factor < number / factor; factor++) {
				while(number % factor == 0) {
					number = number / factor;
					factors.add(factor);
				}
			}
			factors.add(number);
			factors.forEach(factor -> System.out.print(factor + " "));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
