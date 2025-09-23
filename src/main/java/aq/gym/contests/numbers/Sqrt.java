package aq.gym.contests.numbers;

import java.util.stream.IntStream;

public class Sqrt {

//	https://leetcode.com/problems/sqrtx/
	public static void main(String[] args) {
		System.out.println(new Sqrt().mySqrt(25)); //2147395599
	}

	public int mySqrt(int x) {
        if(x == 0) {
        	return 0;
        } else if(x == 1) {
        	return 1;
        } else if(x == 2) {
        	return 1;
        } else if(x == 3) {
        	return 1;
        } else {
        	int sqrt = 0, left = 1, right = x;
        	while(left <= right) {
        		int mid = left + (right - left) / 2;
        		long pow = (long) mid * mid;
        		if(left == right) {
        			if(pow > x) {
        				sqrt = mid - 1;
        			} else {
        				sqrt = mid;
        			}
        			break;
        		} else {
        			if(pow == x) {
            			sqrt = mid;
            			break;
            		} else if(pow > x) {
            			right = left + (right - left) / 2;
            		} else {
            			left = mid + 1;
            		}
        		}
        	}
        	return sqrt;
        }
    }
    
    public int nearestIntegerSqrt(int x) {
        if(x == 0) {
        	return 0;
        } else if(x == 1) {
        	return 1;
        } else if(x == 2) {
        	return 1;
        } else if(x == 3) {
        	return 2;
        } else {
        	int[] numbers = IntStream.iterate(1, num -> num + 1).limit(x).toArray();
        	int sqrt = 0, left = 0, right = numbers.length - 1;
        	while(left <= right) {
        		int mid = left + (right - left) / 2;
        		int pow = numbers[mid] * numbers[mid];
        		if(left == right) {
        			if(pow > x) {
        				int leftPow = numbers[left - 1] * numbers[left - 1];
        				int rightPow = pow;
        				int leftPart = Math.abs(leftPow - x);
        				int rightPart = Math.abs(rightPow - x);
        				if(leftPart < rightPart) {        					
        					sqrt = numbers[left - 1];
        				} else {
        					sqrt = numbers[left];
        				}
        			} else {
        				int leftPow = numbers[left + 1] * numbers[left + 1];
        				int rightPow = pow;
        				int leftPart = Math.abs(leftPow - x);
        				int rightPart = Math.abs(rightPow - x);
        				if(leftPart < rightPart) {        					
        					sqrt = numbers[left + 1];
        				} else {
        					sqrt = numbers[left];
        				}
        			} 
        			return sqrt;
        		} else {
        			if(pow == x) {
            			sqrt = numbers[mid];
            			return sqrt;
            		} else if(pow > x) {
            			right = mid - 1;
            		} else {
            			left = mid + 1;
            		}
        		}
        	}
        	sqrt = numbers[right];
        	return sqrt;
        }
    }
}
