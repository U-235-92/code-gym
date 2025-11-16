package aq.gym.work_with_objects.inheritence;

public class A {

	static {
		System.out.println("Static bloc from A");
	}
	
	{
		System.out.println("No-static bloc from A");
	}
	
	protected int id = 1; // See comment in B
	
	public A() {
//		this(42); // ERR! If you uncomment both of call this() in constructor you will not compile a program
		System.out.println("Constructor from A");
	}
	
	public A(int n) {
//		this(); // ERR! You aren't able to do recursive call of constructors!
	}
	
	protected void fromA() { // See comments in class B
		System.out.println("Call method in class A: From A");
	}
	
	public void youArentAbleShrinkMyVisibility() {
		System.err.println("ERR");
	}
}
