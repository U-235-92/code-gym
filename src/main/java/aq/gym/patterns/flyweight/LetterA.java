package aq.gym.patterns.flyweight;

public class LetterA extends Letter {
	
	protected LetterA() {
		super("A");
	}

	@Override
	public void print(LetterStyle style) {
		System.out.println(this + " " + style);
	}
}
