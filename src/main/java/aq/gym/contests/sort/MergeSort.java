package aq.gym.contests.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			int n = Integer.valueOf(br.readLine());
			long[] nums = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
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
}
