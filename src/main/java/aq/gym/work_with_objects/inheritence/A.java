package aq.gym.work_with_objects.inheritence;

public class A {

	static {
		System.out.println("Static bloc from A");
	}
	
	{
		System.out.println("No-static bloc from A");
	}
	
	public A() {
		System.out.println("Constructor from A");
	}
	
	public void fromA() {
		System.out.println("From A");
	}
}
