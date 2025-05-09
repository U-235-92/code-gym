package aq.gym.contests.other;

public class NumberToZeroStepCounter {

	public static void main(String[] args) {
		System.out.println(new NumberToZeroStepCounter().numberOfSteps(123));
	}

	public int numberOfSteps(int num) {
		int numberOfSteps = 0;
		while(num > 0) {
			if((num & 1) == 0) //The number is even when number's last bite is zero
				num = num >> 1; //It is equal division by 2
			else
				num = num - 1;	
			numberOfSteps++;
		}
		return numberOfSteps;
    }
}
