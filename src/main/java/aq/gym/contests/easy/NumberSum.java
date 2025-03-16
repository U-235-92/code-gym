package aq.gym.contests.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class NumberSum {

	public static void main(String[] args) {
		int number = getNumber();
		getListNumberSumiRecursivenWay(number);
	}

	private static int getNumber() {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		scanner.close();
		return number;
	}
	
	@SuppressWarnings("unused")
	private static void getListNumberSumiRecursivenWay(int number) {
		List<List<Integer>> sums = new ArrayList<>();
		test(1, number, new ArrayList<>(), sums);
		sums.stream()
			.map(list -> list.stream().sorted(Comparator.reverseOrder()).toList())
			.sorted((list1, list2) -> {
				for(int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
					if(!list1.get(i).equals(list2.get(i))) {
						return list1.get(i).compareTo(list2.get(i));
					}
				}
				return Integer.compare(list1.size(), list2.size());
			})
			.map(list -> list.stream().map(String::valueOf).collect(Collectors.joining(" + ")))
			.forEach(System.out::println);
	}
	
	private static void test(int from, int to, List<Integer> nums, List<List<Integer>> sums) {
		if(to == 0) {
			sums.add(new ArrayList<>(nums));
		} else {
			for(int i = from; i <= to; i++) {
				nums.add(i);
				test(i, to - i, nums, sums);
				nums.remove(nums.size() - 1);
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static List<String> getListNumberSumBrootForceWay(int number) {
		Set<String> sumSetStore = new HashSet<String>();
		Queue<int[]> sumQueue = new LinkedList<>();
		String ones = getOnes(number);
		sumSetStore.add(Arrays.stream(ones.split("\\s")).collect(Collectors.joining(" + ")));
		sumQueue.add(Arrays.stream(ones.split("\\s")).mapToInt(Integer::valueOf).toArray());
		while(sumQueue.size() > 0) {
			int[] queueSumNumbers = sumQueue.remove();
			int[] currentSumNumbers = new int[queueSumNumbers.length - 1];
			int[] prevSumNumbers = null;
			for(int i = 0, j = 0; i < queueSumNumbers.length; i += 2, j++) {
				if(i != queueSumNumbers.length - 1) {
					if(i == 0) {						
						currentSumNumbers[i] = queueSumNumbers[i] + queueSumNumbers[i + 1];
						System.arraycopy(queueSumNumbers, i + 2, currentSumNumbers, i + 1, currentSumNumbers.length - 1);
					} else {
						int[] headCopy = Arrays.copyOfRange(prevSumNumbers, 0, j);
						System.arraycopy(headCopy, 0, currentSumNumbers, 0, headCopy.length);
						currentSumNumbers[j] = queueSumNumbers[i] + queueSumNumbers[i + 1];
						if(i < currentSumNumbers.length) {	
							int from = i + 2;
							int to = queueSumNumbers.length;
							int[] tailCopy = Arrays.copyOfRange(queueSumNumbers, from, to);
							int insertPosition = 0;
							while(currentSumNumbers[insertPosition] != 0) {
								insertPosition++;
							}
							System.arraycopy(tailCopy, 0, currentSumNumbers, insertPosition, tailCopy.length);
						} else {
							if(currentSumNumbers[currentSumNumbers.length - 1] == 0) {								
								currentSumNumbers[currentSumNumbers.length - 1] = queueSumNumbers[i];
							}
						}
					}
//					System.out.println(Arrays.toString(currentSumNumbers));
					if(checkSum(currentSumNumbers, number)) {						
						sumQueue.add(currentSumNumbers);
						String sumLine = Arrays.stream(currentSumNumbers).boxed().map(String::valueOf).collect(Collectors.joining(" + "));
						sumSetStore.add(sumLine);
					}
					prevSumNumbers = currentSumNumbers;
					currentSumNumbers = new int[currentSumNumbers.length - 1];
				} else {}
			}
		}
		List<String> result = new ArrayList<String>(sumSetStore);
		result.sort(Comparator.naturalOrder());
		return result;
	}
	
	private static String getOnes(int number) {
		String ones = "";
		for(int i = 0; i < number; i++) {
			ones += "1 ";
		}
		return ones.trim();
	}
 	
	private static boolean checkSum(int[] nums, int target) {
		return target == Arrays.stream(nums).sum();
	}
	
	@SuppressWarnings("unused")
	private static void printNumberSum(List<String> sumSet) {
		sumSet.forEach(System.out::println);
	}
}
