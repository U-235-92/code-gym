package aq.gym.contests.recursion;

import java.util.ArrayList;
import java.util.List;

public class CalculateMoneyInLeetcodeBank {

	public static void main(String[] args) {
		int n = 10;
		System.out.println(new CalculateMoneyInLeetcodeBank().totalMoney(n));
	}

    public int totalMoney(int n) {
    	if(n == 1) return 1;
    	int limitDay = n, currentDay = 1, currentWeek = 1, startPayment = 1;
    	return totalMoney(limitDay, currentDay, currentWeek, startPayment, new ArrayList<List<Integer>>());
    }
    
    private int totalMoney(int limitDay, int currentDay, int currentWeek, int startPayment, List<List<Integer>> weeksTotalPayments) {
    	if(currentDay > 7) {
    		currentDay = 1;
    		currentWeek++;
    	}
    	if(currentDay == 1) {
    		weeksTotalPayments.add(new ArrayList<>());
    		if(currentWeek > 1) startPayment++;
    	}
    	if(limitDay == 1) {
    		List<Integer> currentWeekPayments = weeksTotalPayments.get(currentWeek - 1);
    		calculatePayment(currentWeekPayments, startPayment, currentDay);
    		return weeksTotalPayments.stream().flatMapToInt(list -> list.stream().mapToInt(Integer::valueOf)).sum();
    	}
    	List<Integer> currentWeekPayments = weeksTotalPayments.get(currentWeek - 1);
    	calculatePayment(currentWeekPayments, startPayment, currentDay);
    	return totalMoney(--limitDay, ++currentDay, currentWeek, startPayment, weeksTotalPayments);
    }
    
    private void calculatePayment(List<Integer> currentWeekPayments, int startPayment, int currentDay) {
    	if(currentWeekPayments.isEmpty()) {
    		currentWeekPayments.add(startPayment);
    	} else {
    		int currentPayment = currentWeekPayments.get(currentDay - 2) + 1; 
    		currentWeekPayments.add(currentPayment);
    	}
    }
}
