package aq.gym.work_with_objects.inheritence;

public class Orange extends Fruit {

	private int id = 8;
	
	public Orange() {
		System.out.println("Constructor from Orange");
		id = getId();
	}
	
	@Override
	public int getId() {
		System.out.println("From Orange, id = " + id);
		return id;
	}
	
	public <T> void printT (T t) {
		System.out.println(t);
	}
}
