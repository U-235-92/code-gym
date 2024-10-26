package aq.gym.numbers_numbers_numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberPairs {

	public static void main(String[] args) {
		List<Integer> a = List.of(1, 2, 3);
		List<Integer> b = List.of(3, 4);
		System.out.println(nonStreamWay(a, b).stream().map(arr -> Arrays.toString(arr)).reduce("", (s1, s2) -> String.join(" ", s1, s2)).trim());
		System.out.println(streamWay(a, b).stream().map(arr -> Arrays.toString(arr)).reduce("", (s1, s2) -> String.join(" ", s1, s2)).trim());
	}

	private static List<int[]> nonStreamWay(List<Integer> a, List<Integer> b) {
		List<int[]> result = new ArrayList<>();
		for(int i = 0; i < a.size(); i++) {
			for(int j = 0; j < b.size(); j++) {
				int[] pair = new int[2];
				pair[0] = a.get(i);
				pair[1] = b.get(j);
				result.add(pair);
			}
		}
		return result;
	}
	
	private static List<int[]> streamWay(List<Integer> a, List<Integer> b) {
//		List<int[]> result = a.stream().flatMap(aNum -> b.stream().filter(bNum -> (aNum + bNum) % 3 == 0).map(bNum -> new int[]{aNum, bNum})).collect(Collectors.toList());
		List<int[]> result = a.stream().flatMap(aNum -> b.stream().map(bNum -> new int[]{aNum, bNum})).collect(Collectors.toList());
		return result;
	}
}
