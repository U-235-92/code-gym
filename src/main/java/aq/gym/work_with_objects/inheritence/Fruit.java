package aq.gym.work_with_objects.inheritence;

public class Fruit {

	private int id;
	
	public Fruit() {
		System.out.println("Constructor from Fruit");
		id = getId();
	}
	
	public int getId() {		
		System.out.println("From Fruit, id = " + id);
		return id;
	}
}
