package aq.gym.patterns.flyweight;

public class LetterStyle {

	private int size;
	private String color;
	private String style;
	
	public LetterStyle(int size, String color, String style) {
		this.size = size;
		this.color = color;
		this.style = style;
	}

	@Override
	public String toString() {
		return "LetterStyle [size=" + size + ", color=" + color + ", style=" + style + "]";
	}
}
