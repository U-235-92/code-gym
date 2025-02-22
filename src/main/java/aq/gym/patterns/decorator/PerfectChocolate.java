package aq.gym.patterns.decorator;

public class PerfectChocolate extends Milkshake {

	@Override
	public double getPrice() {
		return 25.00;
	}
	
	@Override
	public String getDescription() {
		return "Perfect chocolate";
	}
}
