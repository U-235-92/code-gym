package aq.gym.work_with_objects.inheritence;

public class ABImlement implements IA, IB {

	@Override
	public void hello() {
		System.out.println("You have to override method even if you define default method in one of interface");
		IA.super.hello(); // BUT! You are able to call default version of interface if you want
	}
}
