package aq.gym.work_with_objects.inheritence;

public class X {

	static{
		System.out.println("Static block X");
	}
	{
		System.out.println("Block X");
	}
	
	X() {
		System.out.println("Call X constructor");
	}
}
