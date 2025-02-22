package aq.gym.patterns.decorator;

public class VanilaBourbon extends Milkshake {

	@Override
	public double getPrice() {
		return 35.00;
	}
	
	@Override
	public String getDescription() {
		return "Vanila bourbon";
	}
}
