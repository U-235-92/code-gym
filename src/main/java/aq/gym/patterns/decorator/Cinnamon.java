package aq.gym.patterns.decorator;

public class Cinnamon extends Condiment {
	
	public Cinnamon(Milkshake milkshake) {
		super(milkshake);
	}

	@Override
	public double getPrice() {
		return getMilkshake().getPrice() + .88;
	}
	
	@Override
	public String getDescription() {
		return getMilkshake().getDescription() + " Cinnamon";
	}
}
