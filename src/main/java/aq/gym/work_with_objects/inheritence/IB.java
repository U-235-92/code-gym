package aq.gym.work_with_objects.inheritence;

@FunctionalInterface
public interface IB {

	void hello(); 
//	void bye(); // ERR! You can define ONLY one abstract method in functional interface 
	
	static void test() {
		System.out.println("You can define a static method in a functional interface");
	}
	
	public default void testDefault() {
		testPrivate();
		System.out.println("You can define a default method in a functional interface");
	}
	
	private void testPrivate() {
		System.out.println("You can define a private method in a functional interface");
	}
}
