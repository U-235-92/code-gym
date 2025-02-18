package aq.gym.numbers_numbers_numbers;

public class GreatestCommonFactor {

	public static void main(String[] args) {
		System.out.println("Brut force style: " + gfcBrutForce(12, 20));
		System.out.println("Euclid style: " + gfcEuclid(12, 20));
	}

	public static int gfcBrutForce(int num1, int num2) {
		int smallest = 0, gfc = -1;
		if(isPositive(num1, num2)) {
			if(!isZero(num1) && !isZero(num2)) {				
				smallest = getSmallest(num1, num2);
				for(int i = 1; i <= smallest; i++) {
					if(num1 % i == 0 && num2 % i == 0) {
						gfc = i;
					}
				}
			}
		}
		return gfc;
	}
	
	public static int gfcEuclid(int num1,int  num2) {
		int greatest = 0, smallest = 0, gfc = -1;
		if(isPositive(num1, num2)) {
			if(!isZero(num1) && !isZero(num2)) {				
				smallest = getSmallest(num1, num2);
				greatest = getGreatest(num1, num2);
				while(greatest % smallest != 0) {
					int tmp = smallest;
					smallest = greatest % smallest;
					greatest = tmp;
				}
				gfc = smallest;
			}
		}
		return gfc;
	}
	
	private static boolean isPositive(int num1, int num2) {
		if(num1 < 0 || num2 < 0)
			throw new IllegalArgumentException("Numbers can't be < 0");
		return true;
	}
	
	private static boolean isZero(int num) {
		return num == 0;
	}
	
	private static int getSmallest(int num1, int num2) {
		int smallest = 0;
		if(num1 > num2)
			smallest = num2;
		else
			smallest = num1;
		return smallest;
	}
	
	private static int getGreatest(int num1, int num2) {
		int greatest = 0;
		if(num1 > num2)
			greatest = num1;
		else
			greatest = num2;
		return greatest;
	}
}
