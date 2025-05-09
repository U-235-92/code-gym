package aq.gym.contests.other;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ClosestNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int guess = 11;
//		int limRnd = 100;
//		int[] arr = {1, 4, 2, 5, 3, 6, 8, 7};
//		Arrays.sort(arr);
//		int[] arr = {1, 2, 3, 8};
//		int[] arr = {1, 2, 3, 10, 12, 22, 35};
//		int[] arr = IntStream.generate(() -> new Random().nextInt(limRnd)).limit(limRnd).toArray();
//		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//		int[] arr = {1, 1, 4, 5, 5, 5, 5, 5, 6, 6, 7, 8, 9, 9, 10, 10, 12, 12, 13, 14, 14, 15, 15}; //ERR RESULT
//		System.out.println(Arrays.toString(arr));
		Scanner scanner = new Scanner(System.in);
		int size = Integer.valueOf(scanner.nextLine());
		int[] arr = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
		int guess = Integer.valueOf(scanner.nextLine());
		scanner.close();
		Arrays.sort(arr);
		int min = arr[0];
		int max = arr[arr.length - 1];
		if(guess >= max) {
			System.out.println(max);
			return;
		} else if(guess <= min) {
			System.out.println(min);
			return;
		} else {
			int startFromMax = Math.abs(guess - max);
			int startFromMin = Math.abs(guess - min);
			int curD = 0;
			int prvD = 0;
			int result = 0;
			if(startFromMax < startFromMin) {
				for(int i = arr.length - 1; i >= 0; i--) {
					curD = arr[i] - guess;
					if(curD == prvD) {
						result = arr[i];
						System.out.println(result);
						return;
					} else if(curD == 0) {
						result = arr[i];
						System.out.println(result);
						return;
					} else if(curD < 0) {
						if(Math.abs(curD) <= prvD) {
							result = arr[i];
							System.out.println(result);
							return;
						} else {
							result = arr[i + 1];
							System.out.println(result);
							return;
						}
					} else {
						result = arr[i];
					}
					prvD = curD;
				}
				System.out.println(result);
			} else {
				for(int i = 0; i < arr.length; i++) {
					curD = guess - arr[i];
					if(curD == prvD) {
						result = arr[i];
						System.out.println(result);
						return;
					} else if(curD == 0) {
						result = arr[i];
						System.out.println(result);
						return;
					} else if(curD < 0) {
						if(Math.abs(curD) <= prvD) {
							result = arr[i];
							System.out.println(result);
							return;
						} else {
							result = arr[i - 1];
							System.out.println(result);
							return;
						}
					} else {
						result = arr[i];
					}
					prvD = curD;
				}
				System.out.println(result);
			}
		}
	}
}
