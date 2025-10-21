package aq.gym.contests.math;

import java.util.Arrays;

public class RichestCustomerWealth {

	public static void main(String[] args) {
		System.out.println(new RichestCustomerWealth().maximumWealth(new int[][] { {1,2,3}, {3,2,1} }));
	}

	public int maximumWealth(int[][] accounts) {
        int maxWealth = Integer.MIN_VALUE;
        for(int i = 0; i < accounts.length; i++) {
        	int currentWealth = Arrays.stream(accounts[i]).sum();
        	if(currentWealth > maxWealth)
        		maxWealth = currentWealth;
        }
        return maxWealth;
    }
}
