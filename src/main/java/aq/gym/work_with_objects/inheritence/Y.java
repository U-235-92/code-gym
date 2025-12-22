package aq.gym.work_with_objects.inheritence;

public class Y extends X {

	static{
		System.out.println("Static block Y");
	}
	{
		System.out.println("Block Y");
		X x = new X();
	}
	
	Y() {
		System.out.println("Call Y constructor");
	}
}
