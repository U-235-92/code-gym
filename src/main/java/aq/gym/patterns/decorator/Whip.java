package aq.gym.patterns.decorator;

public class Whip extends Condiment {

	public Whip(Milkshake milkshake) {
		super(milkshake);
	}

	@Override
	public double getPrice() {
		return getMilkshake().getPrice() + .25;
	}
	
	@Override
	public String getDescription() {
		return getMilkshake().getDescription() + " Whip";
	}
}
