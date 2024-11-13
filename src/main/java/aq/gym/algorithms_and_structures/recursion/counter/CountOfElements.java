package aq.gym.algorithms_and_structures.recursion.counter;

public class CountOfElements {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 3};
		int c = count(numbers, 0);
		System.out.println(c);
	}

	public static int count(int[] numbers, int pos) {
		if(pos == numbers.length) {
			return pos;
		}
		pos += 1;
		return count(numbers, pos);
	}
}
