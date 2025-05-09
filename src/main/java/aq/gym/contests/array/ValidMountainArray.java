package aq.gym.contests.array;

public class ValidMountainArray {

	public static void main(String[] args) {
//		int[] arr = {0,1,2,3,4,5,9,10,11,12,10};
//		int[] arr = {3,7,6,4,3,0,1,0};
		int[] arr = {5,4,3};
		boolean isValidMountain = new ValidMountainArray().validMountainArray(arr);
		System.out.println(isValidMountain);
	}
	
	public boolean validMountainArray(int[] arr) {
		if(arr.length < 3) {
			return false;
		}
		int i = 0;
		while(i < arr.length - 1 && arr[i] < arr[i + 1]) {
			i++;
		}
		if(i == 0 || i == arr.length - 1) {
			return false;
		}
		while(i < arr.length - 1) {
			if(arr[i] <= arr[i + 1]) {
				return false;
			} 
			i++;
		}
		return true;
    }
}
