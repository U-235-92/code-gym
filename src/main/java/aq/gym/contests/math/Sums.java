package aq.gym.contests.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Sums {

	public static void main(String[] args) {
		sumOfMatrixes();
	}

	@SuppressWarnings("unused")
	private static void sumOfMatrixes() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] matrixesData = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = matrixesData[0];
			int m = matrixesData[1];
			int[][] a = new int[n][m];
			int[][] b = new int[n][m];
			int[][] c = new int[n][m];
			for(int i = 0; i < n; i++) {
				int[] line = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				a[i] = line;
			}
			for(int i = 0; i < n; i++) {
				int[] line = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				b[i] = line;
			}
			for(int i = 0; i < a.length; i++) {
				for(int j = 0; j < a[i].length; j++) {
					c[i][j] = a[i][j] + b[i][j];
				}
			}
			for(int[] line : c) {
				for(int num : line) {
					System.out.print(num + " ");
				}
				System.out.println();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void sumOfStrings() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			String[] line1 = br.readLine().split("");
			String[] line2 = br.readLine().split("");
			String[] lineResult = new String[n];
			for(int i = 0; i < n; i++) {
				lineResult[i] = line1[i] + line2[i];
			}
			System.out.println(Arrays.stream(lineResult).collect(Collectors.joining()));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void sumOfTwoNumbers() {
		Scanner scanner = new Scanner(System.in);
		int[] nums = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
		int a = nums[0];
		int b = nums[1];
		scanner.close();
		int c = a + b;
		System.out.println(c);
	}
	
	@SuppressWarnings("unused")
	private static void sumOfTwoPolynomials() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int[] polynomA = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int m = Integer.valueOf(br.readLine());
			int[] polynomB = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int[] polynomC = new int[Integer.max(polynomA.length, polynomB.length)];
			int i = polynomA.length - 1, j = polynomB.length - 1, k = polynomC.length - 1;
			while(i >= 0 && j >= 0) {
				polynomC[k--] = polynomA[i--] + polynomB[j--];
			}
			while(i >= 0 && k >= 0) {
				polynomC[k--] = polynomA[i--];
			}
			while(j >= 0 && k >= 0) {
				polynomC[k--] = polynomB[j--];
			}
			System.out.println((polynomC.length - 1));
			Arrays.stream(polynomC).forEach(num -> System.out.print(num + " "));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
