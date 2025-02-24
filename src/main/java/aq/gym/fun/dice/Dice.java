package aq.gym.fun.dice;

import java.util.concurrent.TimeUnit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

public class Dice {

	public static void main(String[] args) throws InterruptedException {
		while(true) {
			flip();
			TimeUnit.MILLISECONDS.sleep(100);
		}
	}
	
//	Probability of SIX is 3/8;
//	Probability of others is 1/8;
	private static void flip() {
		double rnd = Math.random();
		Side side = null;
		if(rnd <= 0.625) {
			int idx = (int) (Math.random() * 5);
			 side = Side.values()[idx];
		} else {
			side = Side.SIX;
		}
		System.out.println("Side -> " + side.value);
	}
	
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	private enum Side {
		ONE("."), TWO(":"), THREE(":."), FOUR("::"), FIVE(":.:"), SIX(":::");
		private String value;
	}

}
