package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumOfTwo {

	public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine();
		long result = Arrays.stream(line.split("\\s")).mapToLong(Long::valueOf).sum();
		System.out.println(result);
        reader.close();
    }

}
