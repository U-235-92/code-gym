package aq.gym.inheritence;

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
