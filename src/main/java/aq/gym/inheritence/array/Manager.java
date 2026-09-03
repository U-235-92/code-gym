package aq.gym.inheritence.array;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Manager extends Employee {

	private int bonus;

	public Manager(String name, int salary, int bonus) {
		super(name, salary);
		this.bonus = bonus;
	}

}
