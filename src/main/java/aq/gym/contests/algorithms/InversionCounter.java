package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InversionCounter {
	
	private static int alternativeInverseCounter = 0;

	public static void main(String[] args) {
		calculateInversionNumber();
	}
	
	@SuppressWarnings("unused")
	private static void calculateInversionNumber() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int inversionNumber = getInversionNumber(numbers);
			System.out.println(inversionNumber);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int getInversionNumber(int[] numbers) {
		int inversionNumber = split(numbers);
		return inversionNumber;
	}
	
	private static int split(int[] numbers) {
		if(numbers.length <= 1) {
			return 0;
		}
		int[] left = Arrays.copyOf(numbers, numbers.length / 2);
		int[] right = Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length);
		int l = split(left);
		int r = split(right);
		int m = merge(left, right, numbers);
		return m + l + r;
	}
	
	private static int merge(int[] left, int[] right, int[] origin) {
		int leftIdx = 0, rightIdx = 0, originIdx = 0;
		int inverseCount = 0;
		while(leftIdx < left.length && rightIdx < right.length) {
			if(left[leftIdx] <= right[rightIdx]) {
				origin[originIdx] = left[leftIdx];
				leftIdx++;
			} else {
				inverseCount = inverseCount + (left.length - leftIdx); 
				alternativeInverseCounter = alternativeInverseCounter + (left.length - leftIdx);
				origin[originIdx] = right[rightIdx];
				rightIdx++;	
			}
			originIdx++;
		}
		while(leftIdx < left.length) {
			origin[originIdx] = left[leftIdx];
			originIdx++; leftIdx++;
		}
		while(rightIdx < right.length) {
			origin[originIdx] = right[rightIdx];
			originIdx++; rightIdx++;
		}
		return inverseCount;
	}
}
