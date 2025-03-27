package aq.gym.contests.algorithms.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GreedyMaximalSalary {

	public static void main(String[] args) {
		getMaximalSalary();
	}
	
	private static void getMaximalSalary() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			int n = Integer.valueOf(br.readLine());
			List<Long> numbers = Stream.of(br.readLine().split("\\s"))
					.map(Long::valueOf)
					.collect(Collectors.toCollection(ArrayList::new));
			StringBuilder salary = new StringBuilder();
			if(numbers.size() == 1) {
				salary.append(numbers.get(0));
				System.out.println(salary);
			} else {
				while(numbers.size() > 0) {
					long max = 0L;
					int idx = 0;
					for(int i = 0; i < numbers.size(); i++) {
						if(isBetterNumber(numbers.get(i), max)) {
							max = numbers.get(i);
							idx = i;
						}
					}
					salary.append(max);
					numbers.remove(idx);
				}
				System.out.println(salary.toString());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isBetterNumber(long firstNumber, long secondNumber) {
		String firstVariant = "" + firstNumber + secondNumber;
		String secondVariant = ("" + secondNumber + firstNumber).replace("-", "");
		return Long.valueOf(firstVariant).longValue() > Long.valueOf(secondVariant).longValue();
	}
}
