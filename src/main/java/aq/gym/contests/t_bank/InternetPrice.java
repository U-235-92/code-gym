package aq.gym.contests.t_bank;

import java.util.Arrays;
import java.util.Scanner;

public class InternetPrice {

//	https://edu.tbank.ru/selection/76378fbd-1998-48fa-944e-eb736d321f11/practice/244/task/1
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] data = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		int price = new InternetPrice().calculatePrice(data[0], data[1], data[2], data[3]);
		System.out.println(price);
		sc.close();
	}

	private int calculatePrice(int pricePerMonth, int totalMbPerTariff, int additionPriceForMb, int guessTrafficAmount) {
		int price = 0;
		if(guessTrafficAmount > totalMbPerTariff) {			
			price = pricePerMonth + (additionPriceForMb * (guessTrafficAmount - totalMbPerTariff));
		} else {
			price = pricePerMonth;
		}
		return price;
	}
}
