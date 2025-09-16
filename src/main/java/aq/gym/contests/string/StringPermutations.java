package aq.gym.contests.string;

public class StringPermutations {

	public static void main(String[] args) {
		String str = "ABC";
		char[] arr = str.toCharArray();
		int l = 0, r = arr.length - 1;
		permutate(arr, l, r);
	}

	private static void permutate(char[] arr, int l, final int r) {
		if(l == r) {			
			System.out.println(new String(arr));
		} else {
			for(int i = l; i <= r; i++) {
				swap(arr, l, i);
				permutate(arr, l + 1, r);
				swap(arr, l, i);
			}
		}
	}
	
	private static void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
