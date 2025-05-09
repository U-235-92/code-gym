package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeaderElement {

	public static void main(String[] args) {
		getThreeLeaderElements();
	}

	private static void getThreeLeaderElements() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).sorted().toArray();
			int detectMark = getThreeLeaderElementsMarkUsingMap(numbers);
			System.out.println(detectMark);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getThreeLeaderElementsMarkUsingMap(int[] numbers) {
		Map<Integer, Long> leaderMap = Arrays
				.stream(numbers)
				.boxed()
				.collect(Collectors.groupingBy(k -> k, Collectors.counting()));
		long leaderDetectorValue = leaderMap
				.values()
				.stream()
				.filter(val -> val > numbers.length / 4)
				.count();
		if(leaderDetectorValue >= 3) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@SuppressWarnings("unused")
	private static void getOneLeaderElement() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).sorted().toArray();
			int detectMark = getOneLeaderElementMarkUsingMap(numbers);
			System.out.println(detectMark);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getOneLeaderElementMarkUsingMap(int[] numbers) {
		Map<Integer, Long> leaderMap = Arrays
				.stream(numbers)
				.boxed()
				.collect(Collectors.groupingBy(k -> k, Collectors.counting()));
		long leaderDetectorValue = leaderMap
				.values()
				.stream()
				.filter(val -> val > numbers.length / 2)
				.findFirst()
				.orElse(-1L);
		if(leaderDetectorValue == -1) {
			return 0;
		} else {
			return 1;
		}
	}
	
	private static int getOneLeaderElementMarkUsingRecursion(int[] numbers) {
		int[] copy = Arrays.copyOf(numbers, numbers.length);
		int leaderDetectorValue = splitSequence(copy, numbers);
		if(leaderDetectorValue == -1) {
			return 0;
		} else {
			return 1;
		}
	}
	
	private static int splitSequence(int[] split, int[] orig) {
		if(split.length > 1) {
			int[] leftHalf = Arrays.copyOf(split, split.length / 2);
			int[] rightHalf = Arrays.copyOfRange(split, split.length / 2, split.length);
			int leftLeader = splitSequence(leftHalf, orig);
			int rightLeader = splitSequence(rightHalf, orig);
			return Integer.max(leftLeader, rightLeader);
		} 
		int leader = detectLeaderElement(split[0], orig);	
		return leader;
	}
	
	private static int detectLeaderElement(int leader, int[] numbers) {
		int leaderCount = 0;
		int numbersIdx = 0;
		while(numbersIdx < numbers.length) {
			if(numbers[numbersIdx] == leader) {
				leaderCount++;
			} 
			numbersIdx++;
		}
		if(leaderCount > numbers.length / 2) {				
			return leader;
		} 
		return -1;
	}
	
	@SuppressWarnings("unused")
	private static class Test {
		
		private static void stressTestOneLeaderElement() {
			while(true) {
				int[] numbers = IntStream.generate(() -> (int) (Math.random() * 11)).limit(4).sorted().toArray();
				int usingMap = getOneLeaderElementMarkUsingMap(numbers);
				int usingRecursion = getOneLeaderElementMarkUsingRecursion(numbers);
				if(usingMap == usingRecursion) {
					System.out.println("OK");
				} else {
					System.out.println("W/A");
					System.out.println("Map: " + usingMap);
					System.out.println("Rec: " + usingRecursion);
					System.out.println(Arrays.toString(numbers));
					return;
				}
			}
		}
		
		private static void recursionTestOneLeaderElement() {
			int[] numbers = {6, 10, 10, 10};
			int mark = getOneLeaderElementMarkUsingRecursion(numbers);
			System.out.println(mark);
		}
	}
}
