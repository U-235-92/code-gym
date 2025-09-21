package aq.gym.work_with_objects.method_params;

public class Child {

	private String name;
	protected Toy toy;
	
	public Child(String name, Toy toy) {
		super();
		this.name = name;
		this.toy = toy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Toy getToy() {
		return toy;
	}

	public void setToy(Toy toy) {
		this.toy = toy;
	}

	@Override
	public String toString() {
		return "Child [name=" + name + ", toy=" + toy + "]";
	}
}
