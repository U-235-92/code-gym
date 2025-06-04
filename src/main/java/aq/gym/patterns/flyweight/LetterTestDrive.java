package aq.gym.patterns.flyweight;

public class LetterTestDrive {

	public static void main(String[] args) {
		final String a = "a", b = "b";
		Letter a1 = LetterFactory.getInstace().getLetter(a);
		Letter a2 = LetterFactory.getInstace().getLetter(a);
		Letter b1 = LetterFactory.getInstace().getLetter(b);
		a1.print(new LetterStyle(10, "Black", "Bold"));
		a2.print(new LetterStyle(20, "Blue", "Cursive"));
		b1.print(new LetterStyle(58, "Neon", "Regular"));
	}
}
