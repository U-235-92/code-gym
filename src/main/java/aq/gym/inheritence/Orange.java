package aq.gym.inheritence;

public class Orange extends Fruit {

	private int id = 8;
	
	public Orange() {
		System.out.println("Constructor from Orange");
		id = getId();
	}
	
	@Override
	public int getId() {
		System.out.println("From Fruit, id = " + id);
		return id;
	}
	
	public <T> void printT (T t) {
		System.out.println(t);
	}
}
