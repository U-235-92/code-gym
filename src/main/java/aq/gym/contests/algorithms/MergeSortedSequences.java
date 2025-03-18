package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class MergeSortedSequences {

	public static void main(String[] args) {
		doSort();
	}
	
	private static void doSort() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<List<Long>> sequences = new ArrayList<>();
			int n = Integer.valueOf(br.readLine());
			for(int i = 0; i < n; i++) {
				int m = Integer.valueOf(br.readLine());
				String[] arr = br.readLine().split("\\s");
				List<Long> sequence = new ArrayList<>();
				for(int j = 0; j < m; j++) {
					sequence.add(Long.valueOf(arr[j]));
				}
				sequences.add(sequence);
			}
			long[] nums = sequences.stream().flatMap(list -> list.stream()).mapToLong(Long::valueOf).toArray();
			sort(nums);
			Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
 		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void sort(long[] sequences) {
		if(sequences.length == 1) {
			return;
		}
		long[] left = Arrays.copyOf(sequences, sequences.length / 2);
		long[] right = Arrays.copyOfRange(sequences, sequences.length / 2, sequences.length);
		sort(left);
		sort(right);
		merge(left, right, sequences);
	}
	
	private static void merge(long[] left, long[] right, long[] mergedSequence) {
		int leftRunner = 0, rightRunner = 0, mergedSeqRunner = 0;
		while(leftRunner < left.length && rightRunner < right.length) {
			if(left[leftRunner] < right[rightRunner]) {
				mergedSequence[mergedSeqRunner] = left[leftRunner];
				leftRunner++; 
			} else {
				mergedSequence[mergedSeqRunner] = right[rightRunner];
				rightRunner++; 
			}
			mergedSeqRunner++;
		}
		while(leftRunner < left.length) {
			mergedSequence[mergedSeqRunner] = left[leftRunner];
			leftRunner++; mergedSeqRunner++;
		}
		while(rightRunner < right.length) {
			mergedSequence[mergedSeqRunner] = right[rightRunner];
			rightRunner++; mergedSeqRunner++;
		}
	}
	
	@SuppressWarnings("unused")
	private static void testRandomFill() {
		int n = 20;
		List<List<Long>> sequences = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			int m = 5000;//(int) (1 + Math.random() * 5);
			List<Long> sequence = LongStream.generate(() -> 1L + (long) (Math.random() * 1000000000)).limit(m).boxed().toList();
			sequences.add(sequence);
		}
		long[] nums = sequences.stream().flatMap(list -> list.stream()).mapToLong(Long::valueOf).toArray();
		long start = System.currentTimeMillis();
		sort(nums);
		Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
		System.out.println();
		long end = System.currentTimeMillis();
		System.out.println((end - start));
		
	}
	
	@SuppressWarnings("unused")
	private static void testStaticFill() {
		List<List<Long>> sequences = new ArrayList<>();
		List<Long> seq1 = new ArrayList<>(List.of(1L, 2L, 3L));
		List<Long> seq2 = new ArrayList<>(List.of(1L, 2L));
		List<Long> seq3 = new ArrayList<>(List.of(1L, 3L, 5L, 6L, 7L));
		sequences.add(seq1); sequences.add(seq2); sequences.add(seq3);
		long[] nums = sequences.stream().flatMap(list -> list.stream()).mapToLong(Long::valueOf).toArray();
		Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
		System.out.println();
		sort(nums);
		Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
	}
}
