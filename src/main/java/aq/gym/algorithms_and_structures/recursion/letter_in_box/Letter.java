package aq.gym.algorithms_and_structures.recursion.letter_in_box;

public class Letter {

	private String text;

	public Letter() {
		super();
	}

	public Letter(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Letter [text=" + text + "]";
	}

}
