package aq.gym.contests.other;

public class EvenNumbersOfDigits {

	public static void main(String[] args) {
		int[] arr = {55555, 33, 11};
		System.out.println(new EvenNumbersOfDigits().findNumbers2(arr));
	}
	
	public int findNumbers(int[] nums) {
		int evenNumbersOfDigitsCounter = 0;
		for(int num : nums) {
			String str = String.valueOf(num);
			if((str.length() & 1) == 0) {
				evenNumbersOfDigitsCounter++;
			}
		}
		return evenNumbersOfDigitsCounter;
    }
	
	public int findNumbers2(int[] nums) {
		int evenNumbersOfDigitsCounter = 0;
		for(int num : nums) {
			int log = (int) Math.log10(num) + 1;
			if((log & 1) == 0) {
				evenNumbersOfDigitsCounter++;
			}
		}
		return evenNumbersOfDigitsCounter;
    }
}
