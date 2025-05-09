package aq.gym.contests.other;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DuplicateZeros {

	public static void main(String[] args) {
		int[] arr = {8, 7, 0, 2, 0};
		System.out.println(Arrays.toString(new DuplicateZeros().linear(arr)));
		System.out.println(Arrays.toString(new DuplicateZeros().bruteforce(arr)));
//		new DuplicateZeros().test();
	}

	public void duplicateZeros(int[] arr) {
		int[] duplicates = new int[arr.length];
		int length = 0, i = 0;
		while(length < duplicates.length) {
			if(arr[i] == 0) {
				duplicates[length++] = 0;
				if(length < duplicates.length) {					
					duplicates[length++] = 0;
				}
			} else {
				duplicates[length++] = arr[i];
			}
			i++;
		}
		arr = duplicates;
//		System.out.println(Arrays.toString(arr));
	}
	
	public void test() {
		while(true) {
			int[] arr = IntStream.generate(() -> (int) (Math.random() * 10)).limit(5).toArray();
//			int[] arr = {1,0,2,3,0,4,5,0};
			int[] l = linear(arr);
			int[] b = bruteforce(arr);
			if(Arrays.equals(l, b)) {
				System.out.println("OK " + Arrays.toString(arr) + " " + Arrays.toString(l) + " " + Arrays.toString(b));
			} else {
				System.out.println("W/A");
				System.out.println("Arr: " + Arrays.toString(arr));
				System.out.println("L: " + Arrays.toString(l));
				System.out.println("B: " + Arrays.toString(b));
				break;
			}
		}
		
	}
	
	public int[] linear(int[] arr) {
		int[] duplicates = new int[arr.length];
		int length = 0, i = 0;
		while(length < duplicates.length) {
			if(arr[i] == 0) {
				duplicates[length++] = 0;
				if(length < duplicates.length) {					
					duplicates[length++] = 0;
				}
			} else {
				duplicates[length++] = arr[i];
			}
			i++;
		}
		return duplicates;
	}
	
	public int[] bruteforce(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i] == 0) {
				for(int j = arr.length - 2; j > i; j--) {
					arr[j + 1] = arr[j];
				}
				arr[i + 1] = 0;
				i++;
			}
		}
		return arr;
	}
}
