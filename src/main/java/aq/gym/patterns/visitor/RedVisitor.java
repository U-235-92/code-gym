package aq.gym.patterns.visitor;

public class RedVisitor implements Visitor {

	@Override
	public void whiteReverseVisit(Node node) {}

	@Override
	public void redReverseVisit(Node node) {
		StringBuilder sb = new StringBuilder(node.getName());
		System.err.println(sb.reverse());
	}
}
