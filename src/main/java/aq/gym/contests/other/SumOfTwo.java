package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class SumOfTwo {

	public static void main(String[] args) throws IOException {
		sumStream();
	}

	public static void sumStream() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine();
		long result = Arrays.stream(line.split("\\s")).mapToLong(Long::valueOf).sum();
		System.out.println(result);
		reader.close();
	}
	
	public static void sumFile() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt"))));
		String line = reader.readLine();
		long result = Arrays.stream(line.split("\\s")).mapToLong(Long::valueOf).sum();
		PrintWriter writer = new PrintWriter(new File("output.txt"));
		writer.print(result);
		reader.close();
		writer.close();
	}
}
