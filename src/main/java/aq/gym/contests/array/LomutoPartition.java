package aq.gym.contests.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.LongStream;

public class LomutoPartition {

	public static void main(String[] args) {
		app();
	}

	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			lomuto(numbers);
			Arrays.stream(numbers).forEach(num -> System.out.print(num + " "));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void lomuto(long[] numbers) {
		long pivot = numbers[0];
		int i = 0;
		for(int j = 0; j < numbers.length; j++) {
			if(numbers[j] <= pivot) {
				long tmp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = tmp;
				i++;
			} 
		}
		long tmp = numbers[i - 1];
		numbers[i - 1] = pivot;
		numbers[0] = tmp;
	}
	
	@SuppressWarnings("unused")
	private static void dynamicTest() {
		long numberValueLimit = 10;
		long arrayLengthLimit = 10;
		long[] numbers = LongStream
				.generate(() -> (long) (Math.random() * numberValueLimit) + 1)
				.limit(arrayLengthLimit )
				.toArray();
		Arrays.stream(numbers).forEach(num -> System.out.print(num + " "));
		System.out.println();
		long start = System.currentTimeMillis();
		lomuto(numbers);
		Arrays.stream(numbers).forEach(num -> System.out.print(num + " "));
		long end = System.currentTimeMillis();
		System.out.println();
		System.out.println(Duration.between(Instant.ofEpochMilli(start), Instant.ofEpochMilli(end)).toMillis());
	}
	
	@SuppressWarnings("unused")
	private static void staticTest() {
		long[] numbers = new long[]{4, 7, 2, 5, 3, 1, 8, 9, 6};
		long[] numbers2 = new long[]{3, 4, 7, 17};
		long[] numbers3 = new long[]{1, 3, 2, 9, 10};
		System.out.println(Arrays.toString(numbers));
		lomuto(numbers);
		System.out.println(Arrays.toString(numbers));	
	}
}
