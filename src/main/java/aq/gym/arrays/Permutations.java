package aq.gym.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Permutations {

	public static void main(String[] args) {
		int[] arr = IntStream.rangeClosed(1, 10).toArray();
		System.out.println(Arrays.toString(arr));
		turn(arr);
		rndPermutation(arr);
	}

	private static void turn(int[] arr) {
		int n = arr.length - 1;
		for(int i = 0; i < arr.length / 2; i++) {
			int tmp = arr[i];
			arr[i] = arr[n - i];
			arr[n - i] = tmp;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	private static void rndPermutation(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			int rnd = i + (int) (Math.random() * (arr.length - i));
			int tmp = arr[i];
			arr[i] = arr[rnd];
			arr[rnd] = tmp;
		}
		System.out.println(Arrays.toString(arr));
	}
}
