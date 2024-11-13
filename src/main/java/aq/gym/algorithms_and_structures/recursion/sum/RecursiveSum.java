package aq.gym.algorithms_and_structures.recursion.sum;

public class RecursiveSum {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 3};
		int sum = sum(numbers);
		System.out.println(sum);
	}
	
	private static int sum(int[] numbers) {
		int[] sub = new int[numbers.length - 1];
		System.arraycopy(numbers, 0, sub, 0, sub.length);
 		if(sub.length == 0) {
			return numbers[0];
		}
 		int last = numbers[numbers.length - 1];
		return last + sum(sub);
	}

}
