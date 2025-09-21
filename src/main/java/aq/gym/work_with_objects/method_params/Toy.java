package aq.gym.work_with_objects.method_params;

public class Toy {

	private String name;

	public Toy(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Toy [name=" + name + "]";
	}
}
