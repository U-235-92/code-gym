package aq.gym.work_with_objects.clone;

import java.util.ArrayList;

public class TaskStorage implements Cloneable {

	private int id;
	private Person responsible;
	private ArrayList<Task> tasks;
	
	public TaskStorage(int id, Person responsible, ArrayList<Task> tasks) {
		super();
		this.id = id;
		this.responsible = responsible;
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public Person getResponsible() {
		return responsible;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TaskStorage clone() throws CloneNotSupportedException {
		TaskStorage clone = (TaskStorage) super.clone();
		clone.responsible = responsible.clone();
		clone.tasks = (ArrayList<Task>) tasks.clone();
		for(int i = 0; i < tasks.size(); i++) {
			clone.tasks.set(i, tasks.get(i).clone());
		}
		return clone;
	}
}
