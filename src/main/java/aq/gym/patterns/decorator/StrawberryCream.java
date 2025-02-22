package aq.gym.patterns.decorator;

public class StrawberryCream extends Milkshake {
	
	@Override
	public double getPrice() {
		return 15.00;
	}
	
	@Override
	public String getDescription() {
		return "Strawberry cream";
	}
}
