package aq.gym.patterns.decorator;


public abstract class Condiment extends Milkshake {
	
	private Milkshake milkshake;
	
	public Condiment(Milkshake milkshake) {
		super();
		this.milkshake = milkshake;
	}
	
	public Milkshake getMilkshake() {
		return milkshake;
	}
}
