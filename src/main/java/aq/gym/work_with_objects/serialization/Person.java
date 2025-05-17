package aq.gym.work_with_objects.serialization;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int age;
	private String name;
	private List<String> hobbies;
	
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	
	public Person(int age, String name, List<String> hobbies) {
		super();
		this.age = age;
		this.name = name;
		this.hobbies = hobbies;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	
	public List<String> getHobbies() {
		return List.copyOf(hobbies);
	}

	@Override
	public String toString() {
		return name + " " + age + " " + hobbies;
	}
}
