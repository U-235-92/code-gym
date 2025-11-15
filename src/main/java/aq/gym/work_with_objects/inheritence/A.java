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
	
	protected void fromA() { // See comments in class B
		System.out.println("Call method in class A: From A");
	}
	
	public void youArentAbleShrinkMyVisibility() {
		System.err.println("ERR");
	}
}
