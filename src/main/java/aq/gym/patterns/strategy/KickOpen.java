package aq.gym.patterns.strategy;

public class KickOpen implements OpenBehaviour {

	@Override
	public void open() {
		System.out.println("Knock-knock motherfucker!");
	}
}
