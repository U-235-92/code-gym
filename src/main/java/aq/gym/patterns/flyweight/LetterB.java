package aq.gym.patterns.flyweight;

public class LetterB extends Letter {

	protected LetterB() {
		super("B");
	}

	@Override
	public void print(LetterStyle style) {
		System.out.println(this + " " + style);
	}
}
