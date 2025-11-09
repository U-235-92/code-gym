package aq.gym.contests.math;

public class CountOperationsToObtainZero {

//	https://leetcode.com/problems/count-operations-to-obtain-zero/
	public static void main(String[] args) {
//		int num1 = 2, num2 = 3;
		int num1 = 100000, num2 = 1;
		System.out.println(new CountOperationsToObtainZero().countOperations(num1, num2));
	}

    public int countOperations(int num1, int num2) {
        int countOperations = 0;
        while(num1 != 0 && num2 != 0) {
        	if(num1 >= num2) {
        		int sub = num1 - num2;
        		num1 = sub;
        		
        	} else {
        		int sub = num2 - num1;
        		num2 = sub;
        	}
        	countOperations++;
        }
        return countOperations;
    }
}
