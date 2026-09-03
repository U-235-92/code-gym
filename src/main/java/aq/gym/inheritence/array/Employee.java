package aq.gym.inheritence.array;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Employee {

	private String name;
	private int salary;
	
	public Employee(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	
	
}
