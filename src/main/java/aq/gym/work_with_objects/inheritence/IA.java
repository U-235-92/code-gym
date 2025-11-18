package aq.gym.work_with_objects.inheritence;

public interface IA {

	default void hello() {
		System.out.println("Hello from IA");
	}
}
