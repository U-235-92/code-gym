package aq.gym.stream.how_to_do;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HowManyTimesAppearNumber {

	public static void main(String[] args) {
		howManyTimesAppearNumberInNumber(1234544, 4);
	}

	private static void howManyTimesAppearNumberInNumber(int inputNumber, int searchNumber) {
		if(searchNumber > 10 || searchNumber < 0) {
			throw new IllegalArgumentException("Wrong serch number: " + searchNumber);
		}
		String inputNumberToString = inputNumber + "";
		String searchNumberToString = searchNumber + "";
		long appearCount = Arrays.stream(inputNumberToString.split(""))
			.flatMap(input -> Arrays.stream(searchNumberToString.split("")).filter(search -> input.equals(search)))
			.collect(Collectors.counting());
		String output = MessageFormat
				.format("The number {0} in number {1} appeared {2, choice, 0#{2} times|1#1 time|2#{2} times}", searchNumber, inputNumber, appearCount);
		System.out.println(output);
	}
}
