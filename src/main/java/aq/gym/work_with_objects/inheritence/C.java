package aq.gym.work_with_objects.inheritence;

public class C extends B {

	static {
		System.out.println("Static bloc from C");
	}
	
	{
		System.out.println("No-static block from C");
	}
	
	public C() {
		System.out.println("Constructor from C");
	}
	
	@Override
	public void abstractFromB() {
		System.out.println("Override abstract method of class B in class C");
	}
}
