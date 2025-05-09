package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;

public class ProgrammerOnBeach {

	public static void main(String[] args) {
		streamWay();
	}
	
	@SuppressWarnings("unused")
	private static void streamWay() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<List<Long>> sunbads = readData(br)
					.parallelStream()
					.map(list -> list.stream().sorted(Comparator.naturalOrder()).toList())
					.toList();
			Function<List<Long>, Long> listToMinXorMapper = list -> {
				long minXor = Long.MAX_VALUE;
				for(int i = 0; i < list.size() - 1; i++) {
					long current = list.get(i);
					long next = list.get(i + 1);
					if((current ^ next) < minXor) {
						minXor = current ^ next;
					}
				}
				return minXor;
			};
			List<Long> xors = sunbads
					.stream()
					.map(listToMinXorMapper)
					.toList();
			xors.forEach(System.out::println);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void brootForceWay() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<List<Long>> sunbeds = readData(br);
			List<List<Long>> diffs = new ArrayList<>();
			for(List<Long> sunbedNumbers : sunbeds) {
				List<Long> diffNumbers = new ArrayList<>();
				for(int i = 0; i < sunbedNumbers.size(); i++) {
					for(int j = i + 1; j < sunbedNumbers.size(); j++) {
						long a = sunbedNumbers.get(i);
						long b = sunbedNumbers.get(j);
						long diffNumber = a ^ b;
						diffNumbers.add(diffNumber);
					}
				}
				diffs.add(diffNumbers);
			}
			for(List<Long> diffNumbers : diffs) {
				diffNumbers.sort(Comparator.naturalOrder());
			}
			diffs.forEach(diffNumbers -> System.out.println(diffNumbers.getFirst()));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<List<Long>> readData(BufferedReader br) throws NumberFormatException, IOException {
		long testNumber = Long.valueOf(br.readLine());
		if(testNumber < 1 || testNumber > 1000) {
			throw new IllegalArgumentException("Wrong testNumber! testNumber = " + testNumber);
		}
		List<List<Long>> sunbeds = new ArrayList<>();
		int sumOfN = 0;
		for(int i = 0; i < testNumber; i++) {
			int n = Integer.valueOf(br.readLine());
			if(n < 2 || n > 1_000_000) {
				throw new IllegalArgumentException("Wrong n! n = " + n);
			}
			sumOfN += n;
			if(sumOfN > 1_000_000) {
				throw new IllegalArgumentException("Too long sumOfN! sumOfN = " + sumOfN);
			}
			List<Long> sunbedNumbers = new ArrayList<>(n);
			String[] numbers = br.readLine().split("\\s");
			for(int j = 0; j < numbers.length; j++) {
				long sunbedNumber = Long.valueOf(numbers[j]);
				if(sunbedNumber < 0 || sunbedNumber > 1_000_000_000) {
					throw new IllegalArgumentException("Wrong sunbedNumber! sunbedNumber = " + n);
				}
				sunbedNumbers.add(sunbedNumber);
			}
			sunbeds.add(sunbedNumbers);
		}
		return sunbeds;
	}
	
	@SuppressWarnings("unused")
	private static void test() {
		int n = 2;
		int sbLimit = 10000;
		List<List<Long>> sunbeds = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			List<Long> sunbedNumbers = LongStream
					.generate(() -> (int) (Math.random() * (1_000_000_000 + 1)))
					.limit(sbLimit)
					.boxed()
					.toList();
			sunbeds.add(sunbedNumbers);
		}
		Function<List<Long>, List<Long>> xorMapper = list -> {
			List<Long> xors = new ArrayList<>();
			for(int i = 0; i < list.size(); i++) {
				for(int j = i + 1; j < list.size(); j++) {
					long a = list.get(i);
					long b = list.get(j);
					long diffNumber = a ^ b;
					xors.add(diffNumber);
				}
			}
			return xors;
		};
		long start = System.currentTimeMillis();
		List<List<Long>> xors = sunbeds
				.parallelStream()
				.map(xorMapper)
				.map(xor -> xor.parallelStream().sorted().toList())
				.toList();
		xors.forEach(xor -> System.out.println(xor.getFirst()));
		long end = System.currentTimeMillis();
		Duration workDuration = Duration.between(Instant.ofEpochMilli(start), Instant.ofEpochMilli(end));
		System.out.println("Duration of work: " + workDuration.toSeconds());
	}
}
