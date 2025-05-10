package aq.gym.work_with_objects.clone;

public class Task implements Cloneable {
	
	private int id;
	private String description;
	
	public Task(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Task clone() throws CloneNotSupportedException {
		return (Task) super.clone();
	}
	
	@Override
	public String toString() {
		return id + " " + description;
	}
}
