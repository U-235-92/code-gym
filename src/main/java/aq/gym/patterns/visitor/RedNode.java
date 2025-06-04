package aq.gym.patterns.visitor;

public class RedNode extends Node {

	public RedNode(String name) {
		super(name, Color.RED);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.redReverseVisit(this);
		super.printRedName();
	}
}
