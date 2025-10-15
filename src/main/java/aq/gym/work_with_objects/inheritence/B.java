package aq.gym.work_with_objects.inheritence;

public abstract class B extends A {

	static {
		System.out.println("Static bloc from B");
	}
	
	{
		System.out.println("No-static block from B");
	}
	
	public B() {
		System.out.println("Constructor from B");
	}
	
	public abstract void abstractFromB();
}
