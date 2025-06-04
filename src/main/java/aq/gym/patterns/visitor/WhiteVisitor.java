package aq.gym.patterns.visitor;

public class WhiteVisitor implements Visitor {

	@Override
	public void whiteReverseVisit(Node node) {
		StringBuilder sb = new StringBuilder(node.getName());
		System.out.println(sb.reverse());
	}

	@Override
	public void redReverseVisit(Node node) {}
}
