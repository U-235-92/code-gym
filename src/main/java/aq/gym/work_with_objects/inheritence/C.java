package aq.gym.work_with_objects.inheritence;

public class C extends B {

	static {
		System.out.println("Static bloc from C");
	}
	
	{
		System.out.println("No-static block from C");
	}
	
	protected int id = 3;
	
	public C() {
		System.out.println("Constructor from C");
	}
	
	@Override
	public void abstractFromB() {
		System.out.println("Call method in class C: Override abstract method of class B in class C");
	}
}
