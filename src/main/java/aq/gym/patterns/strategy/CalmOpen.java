package aq.gym.patterns.strategy;

public class CalmOpen implements OpenBehaviour {

	@Override
	public void open() {
		System.out.println("Hello!");
	}
}
