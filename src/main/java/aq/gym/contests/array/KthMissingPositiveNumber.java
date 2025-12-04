package aq.gym.contests.array;

public class KthMissingPositiveNumber {

//	https://leetcode.com/problems/kth-missing-positive-number
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		int k = 2;
		System.out.println(new KthMissingPositiveNumber().findKthPositive(arr, k));
	}

    public int findKthPositive(int[] arr, int k) {
        int number = 1, i = 0, j = 0;
        int[] missing = new int[k];
        while(j < missing.length) {
        	if(i < arr.length) {
        		if(arr[i] != number) {
        			missing[j] = number;
        			j++; 
        		} else {        			
        			i++;
        		}
        		number++;
        	} else {
        		missing[j++] = number++;
        	}
        }
        return missing[missing.length - 1];
    }
}
