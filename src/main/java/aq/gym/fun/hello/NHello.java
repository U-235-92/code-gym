package aq.gym.fun.hello;

public class NHello {

	public static void main(String[] args) {
		int n = 1000;
		for(int i = 1; i <= n; i++) {
			if(i % 10 == 0) {
				if(i >= 1000) {					
					System.out.println(i + "-nd");
				} else {					
					System.out.println(i + "-th");
				}
			} else if(i % 10 == 1) {
				if(i == 11) {
					System.out.println(i + "-th");
				} else {					
					System.out.println(i + "-st");
				}
			} else if(i % 10 == 2) {
				if(i == 12) {
					System.out.println(i + "-th");
				} else {					
					System.out.println(i + "-nd");
				}
			} else if(i % 10 == 3) {
				if(i == 13) {
					System.out.println(i + "-th");
				} else {					
					System.out.println(i + "-rd");
				}
			} else if(i % 10 > 3) {
				System.out.println(i + "-th");
			} 
		}
	}
}
