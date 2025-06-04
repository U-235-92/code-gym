package aq.gym.patterns.flyweight;

public abstract class Letter {

	protected String letter;
	
	protected Letter(String letter) {
		super();
		this.letter = letter;
	}
	
	public abstract void print(LetterStyle style);

	@Override
	public String toString() {
		return "Letter [letter=" + letter + "]";
	}
}
