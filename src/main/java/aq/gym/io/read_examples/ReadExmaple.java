package aq.gym.io.read_examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadExmaple {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		String greeting = "Hello there! Please, write a string (:";
		Arrays.stream(greeting.split("")).forEach(letter -> {
			System.out.print(letter);
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println();
		readUsingBufferedReaderAsChar();
	}

	private static void readUsingBufferedReaderAsLine() {
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readUsingBufferedReaderAndStream() {
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			bufferedReader.lines().forEach(System.out::println);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readUsingBufferedReaderAndStreamAndCheckNumbers() {
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			bufferedReader
				.lines()
				.flatMap(line -> Arrays.stream(line.split("\\s+|[a-z]+|\\p{Punct}")))
				.map(word -> {
					Pattern pattern = Pattern.compile("[0-9]+");
					Matcher matcher = pattern.matcher(word);
					if(matcher.find()) 
						return matcher.group();
					return "";
				})
				.filter(word -> word.matches("[0-9]+"))
				.forEach(System.out::println);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readUsingBufferedReaderAsChar() {
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			int code;
			while((code = bufferedReader.read()) != -1) {
				char ch = (char) code;
				System.out.print(ch);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
