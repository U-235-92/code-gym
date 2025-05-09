package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LeftView {
	
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int[] result = new int[numbers.length];
			for(int i = numbers.length - 1; i >= 0; i--) {
				int count = 0;
				for(int j = i - 1; j >= 0; j--) {
					if(numbers[i] > numbers[j]) {
						count++;
					} else {
						break;
					}
				}
				result[i] = count;
			}
			for(int view : result) {
				System.out.print(view + " ");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
