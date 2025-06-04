package aq.gym.patterns.visitor;

public class WhiteNode extends Node {

	public WhiteNode(String name) {
		super(name, Color.WHITE);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.whiteReverseVisit(this);
		super.printWhiteName();
	}

}
