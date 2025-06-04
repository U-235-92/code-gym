package aq.gym.patterns.visitor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VisitorTestDrive {

	public static void main(String[] args) throws InterruptedException {
		Visitor redVisitor = new RedVisitor();
		Visitor whiteVisitor = new WhiteVisitor();
		List<Node> whites = List.of(new WhiteNode("Alice"), new WhiteNode("Alexander"));
		List<Node> reds = List.of(new RedNode("Alice"), new RedNode("Alexander"));
		System.out.println("Whites visit...");
		TimeUnit.MILLISECONDS.sleep(500);
		for(Node white : whites) {
			white.accept(whiteVisitor);
			TimeUnit.MILLISECONDS.sleep(500);
		}
		TimeUnit.MILLISECONDS.sleep(500);
		System.err.println("Reds visit...");
		TimeUnit.MILLISECONDS.sleep(500);
		for(Node red : reds) {
			red.accept(redVisitor);
			TimeUnit.MILLISECONDS.sleep(500);
		}
	}
}
