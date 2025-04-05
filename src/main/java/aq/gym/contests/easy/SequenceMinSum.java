package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SequenceMinSum {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int k = Integer.valueOf(br.readLine());
			int[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int result = 0;
			for(int i = 0; i <= numbers.length - k; i++) {
				int[] localCopy = Arrays.copyOfRange(numbers, i, i + k);
				int loclalMin = Arrays.stream(localCopy).summaryStatistics().getMin();
				result += loclalMin;
			}
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
