package aq.gym.work_with_objects.clone;

import java.util.ArrayList;

public class TaskMain {

	public static void main(String[] args) throws CloneNotSupportedException {
		Person alice = new Person(5, "Alice", new Address("Dream", "Happiness"));
		Person alxander = new Person(5, "Alexander", new Address("Dream", "Happiness"));
		Task t1 = new Task(1, "t1");
		Task t2 = new Task(2, "t2");
		Task t3 = new Task(3, "t3");
		ArrayList<Task> tasks1 = new ArrayList<Task>();
		tasks1.add(t1); tasks1.add(t2);
		ArrayList<Task> tasks2 = new ArrayList<Task>();
		tasks2.add(t3); 
		TaskStorage ts1 = new TaskStorage(1, alxander, tasks1);
		TaskStorage ts2 = new TaskStorage(1, alice, tasks2);
		TaskStorage cloneTs1 = ts1.clone();
		TaskStorage cloneTs2 = ts2.clone();
		System.out.println(ts1.getTasks());
		cloneTs1.getTasks().get(0).setDescription("TEST");
		System.out.println(ts1.getTasks());
		System.out.println(cloneTs1.getTasks());
	}
}
