package aq.gym.work_with_objects.inheritence;

public class Z extends X {

	static{
		System.out.println("Static block Z");
	}
	{
		System.out.println("Block Z");
		Y y = new Y();
	}
	
	public static void main(String[] args) {
		Z z = new Z();
	}
}
