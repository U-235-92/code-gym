package aq.gym.patterns.visitor;

public abstract class Node {

	private String name;
	private Color color;
	
	public Node(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	public abstract void accept(Visitor visitor);
	
	public void printWhiteName() {
		System.out.println("Some of white text");
	}
	
	public void printRedName() {
		System.err.println("Some of red text");
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Node [name=" + name + ", color=" + color + "]";
	}
	
	public enum Color {
		RED, WHITE;
	}
}
